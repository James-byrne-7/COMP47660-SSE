package com.springfield.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank private String username;
    @NotBlank private String password;

    public Student(){}
    public Student(Long id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
