package com.springapp.mvc.resource;

import com.springapp.mvc.entity.User;
import org.springframework.hateoas.Resource;

/**
 * Created by Taras S. on 02.02.15.
 */
public class UserResource extends Resource<User> {

    public UserResource(User user) {
        super(user);
    }
}
