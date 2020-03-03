package com.springfield.springboot.exception;

public class ModuleNotFoundException extends Exception {
    public ModuleNotFoundException(long module_id) {
        super(String.format("Book is not found with id : " + module_id));
    }
}
