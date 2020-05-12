package com.springfield.springboot.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<User> getUsers() {
        return users;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }
}