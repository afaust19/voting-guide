package com.faust.votingguide.models.forms;

import com.faust.votingguide.models.Ballot;
import com.faust.votingguide.models.Candidate;
import com.faust.votingguide.models.Measure;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by afaust on 7/19/17.
 */
// wrapper object that holds an instance of each object on ballot
// created so that more than one ModelAttribute can be used in the Ballot Controller (displayBallot handler)


//doesn't need @Entity because it's not a persistent class
public class BallotForm {

    //private int ballotId;

    //@NotNull
    private List<Candidate> candidates;

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
