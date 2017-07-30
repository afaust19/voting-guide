package com.faust.votingguide.controllers;

import com.faust.votingguide.models.Ballot;
import com.faust.votingguide.models.Candidate;
import com.faust.votingguide.models.data.BallotDao;
import com.faust.votingguide.models.data.CandidateDao;
import com.faust.votingguide.models.data.MeasureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by afaust on 7/30/17.
 */
@Controller
public class DataController {

    @Autowired
    BallotDao ballotDao;

    @Autowired
    CandidateDao candidateDao;

    @Autowired
    MeasureDao measureDao;

    public int getTotalNumberBallots() {

        int totalNumberBallots = 0;

        for (Ballot ballot : ballotDao.findAll()) {
            totalNumberBallots += 1;
        }

        return totalNumberBallots;
    }

}
