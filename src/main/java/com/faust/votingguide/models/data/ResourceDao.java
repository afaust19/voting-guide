package com.faust.votingguide.models.data;

import com.faust.votingguide.models.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by afaust on 8/17/17.
 */
@Repository
@Transactional
public interface ResourceDao extends CrudRepository<Resource, Integer> {
}
