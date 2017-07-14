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
    private Candidate mayoralCandidate;

    @NotNull
    private Candidate comptrollerCandidate;

    @NotNull
    private Candidate aldermanicCandidate;

    public Ballot(int id, Candidate mayoralCandidate, Candidate comptrollerCandidate,
                  Candidate aldermanicCaniddate) {
        this.id = id;
        this.mayoralCandidate = mayoralCandidate;
        this.comptrollerCandidate = comptrollerCandidate;
        this.aldermanicCandidate = aldermanicCaniddate;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public Candidate getMayoralCandidate() {
        return mayoralCandidate;
    }

    public void setMayoralCandidate(Candidate mayoralCandidate) {
        this.mayoralCandidate = mayoralCandidate;
    }

    public Candidate getComptrollerCandidate() {
        return comptrollerCandidate;
    }

    public void setComptrollerCandidate(Candidate comptrollerCandidate) {
        this.comptrollerCandidate = comptrollerCandidate;
    }

    public Candidate getAldermanicCandidate() {
        return aldermanicCandidate;
    }

    public void setAldermanicCandidate(Candidate aldermanicCandidate) {
        this.aldermanicCandidate = aldermanicCandidate;
    }
}
