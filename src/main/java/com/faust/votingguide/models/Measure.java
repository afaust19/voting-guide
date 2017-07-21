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

    private boolean selectYes;  //this can be null when creating a new object in database?

    @ManyToMany(mappedBy = "measures")
    private List<Ballot> ballots;


    public Measure(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.selectYes = true;                  //marks all measures as "yes" by default, changed once the measure gets put on the user's ballot
    }

    //Default Constructor

    public Measure() {}

    // Getters and Setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSelectYes() {
        return selectYes;
    }

    public void setSelectYes(boolean selectYes) {
        this.selectYes = selectYes;
    }
}
