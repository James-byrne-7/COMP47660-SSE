package com.springfield.springboot.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true) private String username;
    @NotBlank
    private String password;
    private String firstName;
    private String lastName;
    @NotNull
    private Character sex;
    private String nationality;
    private String studentId;
    private String address;
    private String phoneNumber;
    @Column(unique=true)
    private String email;
    private Long feeBalance;
    @ManyToMany
    @JoinTable(name="user_roles",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName="id")
    )
    private Set<Role> roles;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_modules",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="module_id", referencedColumnName="id")
    )
    private Set<Module> modules;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name="user_transactions",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="transaction_id", referencedColumnName="id")
    )
    private List<Transaction> transactions;

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
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Character getSex() { return sex; }
    public String getNationality() { return nationality; }
    public String getStudentId() {
        return studentId;
    }
    public String getAddress() {
        return address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public Long getFeeBalance() { return feeBalance; }
    public Set<Module> getModules() { return modules; }
    public List<Transaction> getTransactions() { return transactions; }

    public void setId(Long id) { this.id = id; }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setSex(Character sex) { this.sex = sex; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) {this.lastName = lastName; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhoneNumber(String phoneNo) {
        this.phoneNumber = phoneNo;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void setFeeBalance(Long feeBalance) { this.feeBalance = feeBalance; }
    public void setModules(Set<Module> modules) {this.modules = modules; }
    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }
}
