package com.springfield.springboot.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "modules")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String name;
    @NotBlank private String code;
    @NotBlank private String description;
    @NotNull private long capacity;

    @OneToOne
    @JoinTable(name="role_in_module",
            joinColumns = @JoinColumn(name="role_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="module_id", referencedColumnName="id")
    )
    private Role coordinator;

    @Temporal(TemporalType.TIMESTAMP)
    private Date completionDate;

    @ManyToMany(mappedBy = "modules", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> participants;

//    public Module(){}
//    public Module(Long id, String name, String description, long coordinator_id, char isFinished, long maxStudents){
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.coordinator_id = coordinator_id;
//        this.isFinished = isFinished;
//        this.maxStudents = maxStudents;
//    }

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCode() { return code; }
    public String getDescription() {
        return description;
    }
    public long getCapacity() { return capacity; }
    public Role getCoordinator() {
        return coordinator;
    }
    public Date getCompletionDate() {
        return completionDate;
    }
    public Set<User> getParticipants() {
        return participants;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCode(String code) { this.code = code; }
    public void setDescription(String topics) {
        this.description = topics;
    }
    public void setCapacity(long maxStudents) {
        this.capacity = maxStudents;
    }
    public void setCoordinator(Role coordinator) {
        this.coordinator = coordinator;
    }
    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }
    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

}
