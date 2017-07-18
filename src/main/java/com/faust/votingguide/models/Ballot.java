package com.faust.votingguide.models;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by afaust on 7/6/17.
 */
@Entity
public class Ballot {

    @Id
    @GeneratedValue
    private int id;

    @ManyToMany
    private List<Candidate> candidates;

    @ManyToMany
    private List<Measure> measures;

    @OneToOne(mappedBy = "ballot")
    private User user;

    public Ballot(int id) {
        this.id = id;
    }

    public Ballot() {
    }

    // Methods

    public void addCandidateItem(Candidate item) {
        candidates.add(item);
    }

    public void addMeasureItem(Measure item) {
        measures.add(item);
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public User getUser() {
        return user;
    }
}