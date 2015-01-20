package com.springapp.mvc.repository;

import com.springapp.mvc.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'name': {$regex:?0}}")
    List<User> findWithRegex(String name);
}
