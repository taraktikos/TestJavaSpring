package com.springapp.mvc.entity;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private String id;

    private String name;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
