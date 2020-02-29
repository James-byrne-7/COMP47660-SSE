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
    @NotBlank private long coordinator_id;
    @NotBlank private char isFinished = 'N';

    public Module(){}
    public Module(Long id, String name, String topics, long coordinator_id, char isFinished){
        this.id = id;
        this.name = name;
        this.topics = topics;
        this.coordinator_id = coordinator_id;
        this.isFinished = isFinished;
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
    public long getCoordinatorID() {
        return coordinator_id;
    }
    public char getIsFinished() { return isFinished; }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTopics(String topics) {
        this.topics = topics;
    }
    public void setCoordinatorID(long coordinator) { this.coordinator_id = coordinator; }
    public void setIsFinished(char isFinished) { this.isFinished = isFinished; }
}
