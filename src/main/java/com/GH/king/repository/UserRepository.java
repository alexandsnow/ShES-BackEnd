package com.GH.king.repository;

import com.GH.king.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by alex on 2016/1/22.
 */
@Repository
public interface UserRepository extends CrudRepository<User,String>{

    User findByuserId(String id);
    User findByEmail(String email);
}
