package com.springfield.springboot.model;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank private String username;
    @NotBlank private String password;
    private String firstname;
    private String lastname;
    @NotNull private char sex;
    @NotBlank private String role = "Student"; // Default
    private long fees = 0;
    private String nationality;

    public User(){}
    public User(Long id, String username, String password, String firstname, String lastname, char sex, String role, long fees, String nationality){
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.sex = sex;
        this.role = role;
        this.fees = fees;
        this.nationality = nationality;
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
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public char getSex() { return sex; }
    public String getRole() { return role; }
    public long getFees() { return fees; }
    public String getNationality() { return nationality; }

    public void setId(Long id) { this.id = id; }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setSex(char sex) { this.sex = sex; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) {this.lastname = lastname; }
    public void setRole(String role) { this.role = role; }
    public void setFees(long fees) { this.fees = fees; }
    public void setNationality(String nationality) { this.nationality = nationality; }

}
