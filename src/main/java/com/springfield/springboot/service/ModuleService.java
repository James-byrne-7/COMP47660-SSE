package com.springfield.springboot.service;

import com.springfield.springboot.model.Module;
import com.springfield.springboot.model.User;
import org.json.JSONObject;

import java.util.List;

public interface ModuleService {
    JSONObject getSexBreakdown(Module module);
    Module findModule(String code);
    List<Module> getModules();
    boolean isOpenForEnrolment(Module module);
    void addParticipant(Module module, User user);
    void removeParticipant(Module module, User user);
    void save(Module module);
}
