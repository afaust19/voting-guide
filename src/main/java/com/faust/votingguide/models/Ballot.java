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

    public Ballot(int id, List<Candidate> candidates, User user) {
        this.id = id;
        this.candidates = candidates;
        this.user = user;
    }

    public Ballot() {}


    public void addCandidateItem(Candidate item) {
        candidates.add(item);
    }

    public void addMeasureItem(Measure item) {
        measures.add(item);
    }


    public int getId() {
        return id;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}

    //Setters - need these to create new Ballot in processBallot handler? or use addCandidate and addMeasure
