package com.faust.votingguide.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by afaust on 7/6/17.
 */
@Entity
public class Ballot {

    @Id
    @GeneratedValue
    private int id;

    // Default Constructor

    @NotNull
    private String candidate;       //replace this with Candidate object, not string

    public Ballot() {}

    // Getters and Setters


    public int getId() {
        return id;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }
}
