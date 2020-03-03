package com.springfield.springboot.controller;

import com.springfield.springboot.exception.ModuleNotFoundException;
import com.springfield.springboot.exception.UserNotFoundException;
import com.springfield.springboot.model.Involvement;
import com.springfield.springboot.model.InvolvementID;
import com.springfield.springboot.model.User;
import com.springfield.springboot.repository.InvolvementRepository;
import com.springfield.springboot.repository.ModuleRepository;
import com.springfield.springboot.model.Module;
import com.springfield.springboot.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("name")
public class ModuleController {

    @Autowired UserRepository userRepository;
    @Autowired ModuleRepository moduleRepository;
    @Autowired InvolvementRepository involvementRepository;

    @GetMapping("/modules")
    public String viewUserModules(Model model, HttpSession session)
            throws UserNotFoundException, ModuleNotFoundException {
        @SuppressWarnings("unchecked")
        Long userID = (Long) session.getAttribute("CURRENT_USER");
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException(userID));
        model.addAttribute("user", user);

        List<Long> module_ids = involvementRepository.findModuleByUserId(user.getId());
        List<Module> listModules = new ArrayList<Module>();
        for(Long module_id: module_ids){
            Module module = moduleRepository.findById(module_id)
                    .orElseThrow(() -> new ModuleNotFoundException(module_id));
            listModules.add(module);
        }
        session.setAttribute("userModules", listModules);
        model.addAttribute("modules", listModules);
        return "modules";
    }
    @GetMapping("/modules/all")
    public String viewAllModules(Model model, HttpSession session)
            throws UserNotFoundException, ModuleNotFoundException {
        @SuppressWarnings("unchecked")
        Long userID = (Long) session.getAttribute("CURRENT_USER");
        if (session.getAttribute("userModules")==null) return "redirect:modules";
        List<Module> modules = moduleRepository.findAll();
        model.addAttribute("modules", modules);
        return "modules";
    }

    @RequestMapping("/statistics/{moduleID}/{moduleName}")
    public String viewStatistics(@PathVariable(value = "moduleID") long moduleID, @PathVariable(value = "moduleName") String moduleName, Model model) {
        JSONObject data = new JSONObject();
        data.put("female",involvementRepository.countUsersInvolvedBySex(moduleID, 'F') );
        data.put("male", involvementRepository.countUsersInvolvedBySex(moduleID, 'M') );
        model.addAttribute("data", data);

        return "modulestatistics";
    }

    @RequestMapping("/enrol/{moduleID}")
    public String dropModule(@PathVariable(value = "moduleID") Long moduleID, Model model, HttpSession session) throws UserNotFoundException, ModuleNotFoundException {
        Long userID = (Long) session.getAttribute("CURRENT_USER");
        Module module = moduleRepository.findById(moduleID)
                .orElseThrow(() -> new ModuleNotFoundException(moduleID));
        if (module.getIsFinished() == 'N' && module.getMaxStudents() >= 0+involvementRepository.studentsEnrolled(moduleID)) {
            Involvement involvement = new Involvement(userID, moduleID);
            involvementRepository.saveAndFlush(involvement);
        } else {
            model.addAttribute("errorMessage", "Module is full");
        }
        return "redirect:/modules";
    }
    @RequestMapping("/drop/{moduleID}")
    public String enrolModule(@PathVariable(value = "moduleID") Long moduleID, Model model, HttpSession session) throws UserNotFoundException, ModuleNotFoundException {
        Long userID = (Long) session.getAttribute("CURRENT_USER");
        Involvement involvement = involvementRepository.findById(new InvolvementID(userID, moduleID))
                .orElseThrow(() -> new ModuleNotFoundException(moduleID));
        involvementRepository.delete(involvement);
        return "redirect:/modules";
    }




}
