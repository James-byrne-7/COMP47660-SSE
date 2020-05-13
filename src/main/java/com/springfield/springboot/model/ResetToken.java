package com.springfield.springboot.model;

import javax.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class ResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tokenId;
    private String value;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    private boolean isUsed;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public ResetToken() {}
    public ResetToken(User user) {
        this.user = user;
        creationDate = new Date();
        value = UUID.randomUUID().toString();
        isUsed = false;
    }

    public long getTokenId() {
        return tokenId;
    }
    public String getValue() {
        return value;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public boolean isUsed() {
        return isUsed;
    }
    public User getUser() {
        return user;
    }

    public void setTokenId(long tokenid) {
        this.tokenId = tokenid;
    }
    public void setValue(String confirmationToken) {
        this.value = confirmationToken;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public void setUsed(boolean used) {
        isUsed = used;
    }
    public void setUser(User user) {
        this.user = user;
    }
}