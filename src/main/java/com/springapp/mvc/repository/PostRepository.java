package com.springapp.mvc.repository;

import com.springapp.mvc.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

}
