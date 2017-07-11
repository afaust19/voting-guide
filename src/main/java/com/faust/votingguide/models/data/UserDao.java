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

    //method for finding an object by a specific field (username)
    //try to use method in UserController (under processRegisterForm) instead (how to re-use?)

    User findByUsername(String username);


}
