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

    @NotNull
    private int year;

    // Default Constructor

    @NotNull
    private String candidate;

    public Ballot() {}

    // Getters and Setters


    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }
}
