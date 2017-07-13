package com.faust.votingguide.models.data;

import com.faust.votingguide.models.Ward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by afaust on 7/13/17.
 */
@Repository
@Transactional
public interface WardDao extends CrudRepository<Ward, Integer> {
}
