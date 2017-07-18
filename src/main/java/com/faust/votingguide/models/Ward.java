package com.faust.votingguide.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by afaust on 7/13/17.
 */
@Entity
public class Ward {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private int wardNumber;

    @OneToMany
    @JoinColumn(name = "ward_id")
    private List<User> users = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "ward_id")
    private List<Candidate> candidates = new ArrayList<>();

    // list of candidates in each ward

    // list of issues for each ward

    public Ward(int id, int wardNumber) {
        this.id = id;
        this.wardNumber = wardNumber;
    }

    //Default constructor

    public Ward() {}

    public int getId() {
        return id;
    }

    public int getWardNumber() {
        return wardNumber;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }
}
