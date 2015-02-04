package com.springapp.mvc;

import com.springapp.mvc.config.AppConfig;
import com.springapp.mvc.entity.Location;
import com.springapp.mvc.entity.Post;
import com.springapp.mvc.entity.Task;
import com.springapp.mvc.entity.User;
import com.springapp.mvc.repository.LocationRepository;
import com.springapp.mvc.repository.PostRepository;
import com.springapp.mvc.repository.TaskRepository;
import com.springapp.mvc.repository.UserRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class AppTests {

    final static Logger LOG = LoggerFactory.getLogger(AppTests.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Before
    public void setup() {
        locationRepository.deleteAll();
        userRepository.deleteAll();
        postRepository.deleteAll();
        User user1 = new User("mongo");
        User user2 = new User("mongo2");
        Location location = new Location("Home", 40.414773, 20.519687);
        locationRepository.save(location);
        user1.setLocation(location);
        userRepository.save(user1);
        userRepository.save(user2);
        Post post1 = new Post("post", user1);
        Post post2 = new Post("post2", user2);
        postRepository.save(post1);
        postRepository.save(post2);
        user1.getPosts().add(post1);
        user2.getPosts().add(post1);
        user2.getPosts().add(post2);
        userRepository.save(user1);
        userRepository.save(user2);

        Task task = new Task();
        task.setName("task1");
        task.setChecked(false);
        taskRepository.save(task);
    }

    @After
    public void tearDown() {
        //userRepository.deleteAll();
    }

    public Logger getLog() {
        return LOG;
    }
}
