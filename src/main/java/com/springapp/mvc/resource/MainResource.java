package com.springapp.mvc.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Taras S. on 02.02.15.
 */
public class MainResource extends ResourceSupport {

    public static final String LINK_NAME_USERS = "users";

    private final String content;

    @JsonCreator
    public MainResource(@JsonProperty("content") String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
