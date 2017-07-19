package com.faust.votingguide.models.forms;

import com.faust.votingguide.models.Candidate;
import com.faust.votingguide.models.Measure;

import javax.validation.Valid;

/**
 * Created by afaust on 7/19/17.
 */
// wrapper object that holds an instance of each object on ballot
// created so that more than one ModelAttribute can be used in the Ballot Controller (displayBallot handler)

public class DisplayBallotForm {

    @Valid
    private Candidate mayoralCandidate = new Candidate();

    @Valid
    private Candidate comptrollerCandidate = new Candidate();

    @Valid
    private Candidate aldermanicCandidate = new Candidate();

    @Valid
    private Measure measure1 = new Measure();               //how many do instances of measure since there may be more than one? code re-usability

    @Valid
    private Measure measure2 = new Measure();


    // Getters and Setters for each object


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

    public Measure getMeasure1() {
        return measure1;
    }

    public void setMeasure1(Measure measure1) {
        this.measure1 = measure1;
    }

    public Measure getMeasure2() {
        return measure2;
    }

    public void setMeasure2(Measure measure2) {
        this.measure2 = measure2;
    }
}
