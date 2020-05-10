package com.springfield.springboot.model;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String username;
    @NotBlank private String password;
    private String firstname;
    private String lastname;
    @NotNull private Character sex;
    @NotBlank private String role = "Student"; // Default
    private long fees = 0;
    private String nationality;

    public User(){}
    public User(String username, String password, char sex, String nationality){
        this.username = username;
        this.password = password;
        this.sex = sex;
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
    public Character getSex() { return sex; }
    public String getRole() { return role; }
    public Long getFees() { return fees; }
    public String getNationality() { return nationality; }

    public void setId(Long id) { this.id = id; }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setSex(Character sex) { this.sex = sex; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) {this.lastname = lastname; }
    public void setRole(String role) { this.role = role; }
    public void setFees(Long fees) { this.fees = fees; }
    public void setNationality(String nationality) { this.nationality = nationality; }

}
