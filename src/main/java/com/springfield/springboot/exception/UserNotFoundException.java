package com.springfield.springboot.exception;


public class UserNotFoundException extends Exception {
    public UserNotFoundException(String user_id) {
        super("Book is not found with id : " + user_id);
    }
}
