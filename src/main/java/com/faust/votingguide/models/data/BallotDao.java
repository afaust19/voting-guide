package com.faust.votingguide.models.data;

import com.faust.votingguide.models.Ballot;
import com.faust.votingguide.models.Candidate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by afaust on 7/6/17.
 */
@Repository
@Transactional
public interface BallotDao extends CrudRepository<Ballot, Integer> {

}
