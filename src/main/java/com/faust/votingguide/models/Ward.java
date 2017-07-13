package com.faust.votingguide.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
}
