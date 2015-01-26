package com.springapp.mvc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Post {

    @Id
    private String id;

    private String title;

    private String text;

    @DBRef
    private User user;

    public Post() {}

    public Post(String title, User user) {
        this.title = title;
        this.user = user;
        this.user.getPosts().add(this);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
