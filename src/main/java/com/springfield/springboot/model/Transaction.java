package com.springfield.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Long InitialBalance;
    public Long amount;

    public Transaction() {}
    public Transaction(Long balance) {this.InitialBalance = balance; }


    public Long getInitialBalance() {
        return InitialBalance;
    }

    public void setInitialBalance(Long initialBalance) {
        InitialBalance = initialBalance;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
