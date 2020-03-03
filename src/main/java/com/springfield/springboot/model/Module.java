package com.springfield.springboot.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "modules")
public class Module {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank private String name;
    @NotBlank private String topics;
    @NotNull private long coordinator_id;
    @NotNull private char isFinished = 'N';
    @NotNull private long maxStudents;

    public Module(){}
    public Module(Long id, String name, String topics, long coordinator_id, char isFinished, long maxStudents){
        this.id = id;
        this.name = name;
        this.topics = topics;
        this.coordinator_id = coordinator_id;
        this.isFinished = isFinished;
        this.maxStudents = maxStudents;
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
    public long getCoordinator_id() {
        return coordinator_id;
    }
    public char getIsFinished() { return isFinished; }
    public long getMaxStudents() { return  maxStudents; }

    public void setMaxStudents(long maxStudents) {
        this.maxStudents = maxStudents;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTopics(String topics) {
        this.topics = topics;
    }
    public void setCoordinator_id(long coordinator) { this.coordinator_id = coordinator; }
    public void setIsFinished(char isFinished) { this.isFinished = isFinished; }
}
