package com.springfield.springboot.exception;


public class ModuleNotFoundException extends Exception {
    private long module_id;
    public ModuleNotFoundException(long module_id) {
        super(String.format("Book is not found with id : '%s'", module_id));
    }
}
