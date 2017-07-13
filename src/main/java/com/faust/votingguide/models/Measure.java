package com.faust.votingguide.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by afaust on 7/13/17.
 */
@Entity
public class Measure {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    public Measure(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //Default Constructor

    public Measure() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
