package com.springapp.mvc;

import com.springapp.mvc.config.AppConfig;
import com.springapp.mvc.entity.User;
import com.springapp.mvc.repository.UserRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class AppTests {

    final static Logger LOG = LoggerFactory.getLogger(AppTests.class);

    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        userRepository.deleteAll();
        userRepository.save(new User("Ivan"));
        userRepository.save(new User("Petro"));
    }

    @After
    public void tearDown() {
        //userRepository.deleteAll();
    }

    @Test
    public void simple() throws Exception {
        LOG.info("***start simple***");
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"));
       LOG.info("***end simple***");
    }

    @Test
    public void testFindAll() {
        LOG.info("***Find all***");
        userRepository.findAll().forEach(user -> LOG.info(user.toString()));
    }

    @Test
    public void testFindWithRegex() {
        LOG. info("***Find va***");
        userRepository.findWithRegex("va").forEach(user -> LOG.info(user.toString()));
    }
}
