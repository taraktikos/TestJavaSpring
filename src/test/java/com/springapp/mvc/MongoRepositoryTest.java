package com.springapp.mvc;

import com.springapp.mvc.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Taras S. on 27.01.15.
 */
public class MongoRepositoryTest extends AppTests {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup() {
        super.setup();
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
