package com.springfield.springboot.model;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank private String username;
    @NotBlank private String password;
    @NotBlank private String sex;
    @NotBlank private String role = "Student"; // Default
    private long fees = 0;

    public User(){}
    public User(Long id, String username, String password, String sex, String role, long fees){
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.role = role;
        this.fees = fees;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getSex() { return sex; }
    public String getRole() { return role; }
    public long getFees() { return fees; }

    public void setId(Long id) { this.id = id; }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setSex(String sex) { this.sex = sex; }
    public void setRole(String role) { this.role = role; }
    public void setFees(long fees) { this.fees = fees; }

}
