package com.springapp.mvc;

import com.springapp.mvc.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.junit.Assert.*;

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

    @Test
    public void testFindAllPageable() {
        Pageable page = new PageRequest(0, 1);
        LOG. info("***Find 1 page***");
        assertEquals(2, userRepository.findAll(page).getTotalPages());
        assertEquals(1, userRepository.findAll(page).getSize());
        assertEquals("mongo", userRepository.findAll(page).getContent().get(0).getName());
        LOG. info(userRepository.findAll(page).getContent().get(0).getName());

        LOG. info("***Find 2 page***");
        page = page.next();
        assertEquals(1, userRepository.findAll(page).getSize());
        assertEquals("mongo2", userRepository.findAll(page).getContent().get(0).getName());
        LOG. info(userRepository.findAll(page).getContent().get(0).getName());
    }
}
