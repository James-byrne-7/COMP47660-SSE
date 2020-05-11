package com.springfield.springboot.model;

import javax.persistence.*;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true) private String username;
    @NotBlank private String password;
    private String firstname;
    private String lastname;
    @NotNull private Character sex;
    private long fees = 0;
    private String nationality;
    @ManyToMany
    @JoinTable(name="user_roles",
        joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

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
    public Set<Role> getRoles() { return roles; }
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
    public void setRoles(Set<Role> roles) { this.roles = roles; }
    public void setFees(Long fees) { this.fees = fees; }
    public void setNationality(String nationality) { this.nationality = nationality; }

}
