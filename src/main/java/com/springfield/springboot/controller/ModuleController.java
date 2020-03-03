package com.springfield.springboot.controller;

import com.springfield.springboot.exception.*;
import com.springfield.springboot.model.*;
import com.springfield.springboot.model.Module;
import com.springfield.springboot.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ModuleController {

    @Autowired UserRepository userRepository;
    @Autowired ModuleRepository moduleRepository;
    @Autowired InvolvementRepository involvementRepository;

    @GetMapping("/modules")
    public String viewUserModules(Model model, HttpSession session)
            throws UserNotFoundException, ModuleNotFoundException {
        Long userID = (Long) session.getAttribute("CURRENT_USER");
        if (userID == null)
            return "redirect:logout?error=true";

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
        Long userID = (Long) session.getAttribute("CURRENT_USER");
        if (userID == null)
            return "redirect:logout?error=true";
        if (session.getAttribute("userModules")==null) return "redirect:modules";
        List<Module> modules = moduleRepository.findAll();
        model.addAttribute("modules", modules);
        return "modules";
    }

    @RequestMapping("/statistics/{moduleID}/{moduleName}")
    public String viewStatistics(@PathVariable(value = "moduleID") long moduleID, @PathVariable(value = "moduleName") String moduleName, Model model, HttpSession session) {
        Long userID = (Long) session.getAttribute("CURRENT_USER");
        if (userID == null)
            return "redirect:logout?error=true";
        JSONObject data = new JSONObject();
        data.put("female",involvementRepository.countUsersInvolvedBySex(moduleID, 'F') );
        data.put("male", involvementRepository.countUsersInvolvedBySex(moduleID, 'M') );
        model.addAttribute("data", data);

        return "modulestatistics";
    }

    @RequestMapping("/enrol/{moduleID}")
    public String dropModule(@PathVariable(value = "moduleID") Long moduleID, Model model, HttpSession session) throws UserNotFoundException, ModuleNotFoundException {
        Long userID = (Long) session.getAttribute("CURRENT_USER");
        if (userID == null)
            return "redirect:logout?error=true";
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
        if (userID == null)
            return "redirect:logout?error=true";
        Involvement involvement = involvementRepository.findById(new InvolvementID(userID, moduleID))
                .orElseThrow(() -> new ModuleNotFoundException(moduleID));
        involvementRepository.delete(involvement);
        return "redirect:/modules";
    }
    @GetMapping("/edit/{moduleID}")
    public String editModule(@PathVariable(value = "moduleID") Long moduleID, Model model, HttpSession session) throws UserNotFoundException, ModuleNotFoundException {
        if (session.getAttribute("staff") == null){
            return "redirect:/modules";
        }
        Module module = moduleRepository.findById(moduleID)
                .orElseThrow(() -> new ModuleNotFoundException(moduleID));
        model.addAttribute("module", module);
        return "editmodule";
    }
    @PostMapping("/edit/{moduleID}")
    public String updateNote( @ModelAttribute("module")  Module module, Model model) throws ModuleNotFoundException {
        /*Book oldBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        oldBook.setBook_name(book.getBook_name());
        oldBook.setAuthor_name(book.getAuthor_name());
        oldBook.setIsbn(book.getIsbn());*/

        moduleRepository.save(module);

        return "redirect:/modules";
    }




}
