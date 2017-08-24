package com.faust.votingguide.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by afaust on 8/16/17.
 */
@Entity
public class Resource {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User user;

    private ResourceType type; //article, audio, video - will be auto generated depending on where customer adds this (resources page panel)

    private String title;

    private String url;

    public Resource() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
