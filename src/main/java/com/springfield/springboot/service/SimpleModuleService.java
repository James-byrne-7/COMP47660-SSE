package com.springfield.springboot.service;

import com.springfield.springboot.controller.UserController;
import com.springfield.springboot.model.Module;
import com.springfield.springboot.model.ResetToken;
import com.springfield.springboot.model.User;
import com.springfield.springboot.repository.ModuleRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service("moduleService")
public class SimpleModuleService implements ModuleService {
    @Autowired
    ModuleRepository moduleRepository;

    private static final Logger logger = LoggerFactory.getLogger(SimpleUserService.class);

    @Override
    public JSONObject getSexBreakdown(Module module) {
        logger.debug("GENERATING SEX BREAKDOWN");
        int M = 0, F = 0, U = 0;
        for (User u : module.getParticipants())
            if (u.getSex().equals('M')) M++;
            else if (u.getSex().equals('F')) F++;
            else U++;
        JSONObject data = new JSONObject();
        data.put("female", F);
        data.put("male", M);

        return data;
    }
    @Override
    public Module findModule(String code) {
        logger.debug(String.format("RETRIEVING MODULE WITH CODE: %S", code));
        Module module = moduleRepository.findByCode(code);
        if (module == null) logger.debug("MODULE NOT FOUND");
        return module;
    }

    @Override
    public List<Module> getModules() {
        logger.debug("RETRIEVING ALL MODULES");
        return moduleRepository.findAll();
    }

    @Override
    public boolean isOpenForEnrolment(Module module) {
        Date now = new Date();
        long current_time = now.getTime();
        long completion_time = module.getCompletionDate().getTime();
        long expiry_time = current_time + ((long)6*7*24*60*60*1000); // Six Weeks

        boolean closed = current_time > expiry_time;
        if (closed) logger.debug("MODULE COMPLETION DATE PASSED");
        boolean full = (module.getCapacity() <= module.getParticipants().size());
        if (full) logger.debug("MODULE ALREADY FULL");
        return !(full || closed);
    }

    @Override
    public void addParticipant(Module module, User user) {
        logger.debug("RETRIEVEING MODULE PARTICIPANTS");
        module.getParticipants().add(user);
        moduleRepository.save(module);
    }
    @Override
    public void removeParticipant(Module module, User user) {
        module.getParticipants().remove(user);
    }
    @Override
    public void save(Module module) { moduleRepository.save(module); }
}
