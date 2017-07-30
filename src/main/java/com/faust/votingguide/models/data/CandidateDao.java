package com.faust.votingguide.models.data;

import com.faust.votingguide.models.Candidate;
import com.faust.votingguide.models.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by afaust on 7/5/17.
 */
@Repository
@Transactional
public interface CandidateDao extends CrudRepository<Candidate, Integer> {

    Candidate findByName(String name);

}
