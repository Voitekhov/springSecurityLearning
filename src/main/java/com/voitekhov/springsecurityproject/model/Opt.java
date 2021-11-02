package com.voitekhov.springsecurityproject.model;

import javax.persistence.*;

@Entity
@Table(name = "opt")
public class Opt extends BaseEntity {
    public Opt() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "opt")
    private String opt;

    public Opt(User user, String opt) {
        this.user = user;
        this.opt = opt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public User getUser() {
        return user;
    }

    public String getOpt() {
        return opt;
    }
}
