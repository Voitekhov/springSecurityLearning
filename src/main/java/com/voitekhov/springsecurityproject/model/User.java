package com.voitekhov.springsecurityproject.model;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
