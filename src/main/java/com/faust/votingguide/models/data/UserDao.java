package com.faust.votingguide.models.data;

import com.faust.votingguide.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by afaust on 7/5/17.
 */
@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {
}
