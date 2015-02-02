package com.springapp.mvc.assembler;

import com.springapp.mvc.entity.User;
import com.springapp.mvc.resource.UserResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * Created by Taras S. on 02.02.15.
 */
@Component
public class UserResourceAssembler implements ResourceAssembler<User, UserResource> {

    @Override
    public UserResource toResource(User user) {
        UserResource resource = new UserResource(user);
        return resource;
    }
}
