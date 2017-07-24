package com.faust.votingguide.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

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


    @ManyToMany(mappedBy = "measures")
    private List<Ballot> ballots;

    public Measure(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;                                                                                     //marks all measures as "yes" by default, changed once the measure gets put on the user's ballot
    }


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

    public List<Ballot> getBallots() {
        return ballots;
    }
}
