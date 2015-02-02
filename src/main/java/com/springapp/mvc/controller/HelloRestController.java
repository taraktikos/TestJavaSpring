package com.springapp.mvc.controller;

import com.springapp.mvc.assembler.UserResourceAssembler;
import com.springapp.mvc.entity.User;
import com.springapp.mvc.repository.UserRepository;
import com.springapp.mvc.resource.MainResource;
import com.springapp.mvc.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping( value = "/rest", produces = "application/hal+json" )
public class HelloRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @RequestMapping("")
	public HttpEntity<MainResource> getHomePage() {
        MainResource mainResource = new MainResource("Test hypermedia api rest application");
        mainResource.add(linkTo(methodOn(HelloRestController.class).getHomePage()).withSelfRel());
        mainResource.add(linkTo(methodOn(HelloRestController.class).getUsers(null, null)).withRel(MainResource.LINK_NAME_USERS));

        return new ResponseEntity<>(mainResource, HttpStatus.OK);
	}

    @RequestMapping(value = "/users")
	public HttpEntity<PagedResources<UserResource>> getUsers(@PageableDefault(size = 2, page = 0)Pageable pageable, PagedResourcesAssembler<User> assembler) {
        Page<User> users = userRepository.findAll(pageable);
        return new ResponseEntity<>(assembler.toResource(users, userResourceAssembler), HttpStatus.OK);
	}


}