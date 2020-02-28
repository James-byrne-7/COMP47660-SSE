package com.springfield.springboot.model;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "modules")
public class Module {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank private String name;
    @NotBlank private String topics;
    @NotBlank private String coordinator;
    @ManyToMany(mappedBy = "modules", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<User> users;

    public Module(){}
    public Module(Long id, String name, String topics, String coordinator){
        this.id = id;
        this.name = name;
        this.topics = topics;
        this.coordinator = coordinator;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getTopics() {
        return topics;
    }
    public String getCoordinator() {
        return coordinator;
    }
    public Set<User> getUsers() { return users; }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTopics(String topics) {
        this.topics = topics;
    }
    public void setCoordinator(String coordinator) { this.coordinator = coordinator; }
    public void setUsers(Set<User> users) { this.users = users; }
}
