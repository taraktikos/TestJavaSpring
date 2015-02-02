package com.springapp.mvc.controller;

import com.springapp.mvc.AppTests;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;

/**
 * Created by Taras S. on 27.01.15.
 */
public class HelloRestTest extends AppTests {

    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        super.setup();
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void getHomePageShouldReturnLinks() throws Exception {
        mockMvc.perform(get("/rest"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$.content", is("Test hypermedia api rest application")))
                .andExpect(jsonPath("$._links.self.href", endsWith("/rest")))
                .andExpect(jsonPath("$._links.users.href", endsWith("/rest/users")))
        ;
    }

    @Test
    public void getUsersTest() throws Exception {
        mockMvc.perform(get("/rest/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON))
        ;
        
    }
}
