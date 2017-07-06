package com.faust.votingguide.models.forms;

import com.faust.votingguide.models.Candidate;

import javax.validation.Valid;

/**
 * Created by afaust on 7/6/17.
 */

// wrapper object that holds an instance of each candidate object being compared
// created so that more than one ModelAttribute can be used in the Candidate Controller (compare handler)

public class CompareForm {   //https://stackoverflow.com/questions/4665587/spring-mvc-bind-into-2-objects-that-have-identical-fields

    @Valid
    private Candidate compare1 = new Candidate();

    @Valid
    private Candidate compare2 = new Candidate();

    public Candidate getCompare1() {
        return compare1;
    }

    public void setCompare1(Candidate compare1) {
        this.compare1 = compare1;
    }

    public Candidate getCompare2() {
        return compare2;
    }

    public void setCompare2(Candidate compare2) {
        this.compare2 = compare2;
    }
}
