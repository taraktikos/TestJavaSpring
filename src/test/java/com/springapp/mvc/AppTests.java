package com.springapp.mvc;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.springapp.mvc.config.AppConfig;
import com.springapp.mvc.entity.Post;
import com.springapp.mvc.entity.User;
import com.springapp.mvc.repository.PostRepository;
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
import org.w3c.dom.html.HTMLHeadingElement;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
public class AppTests {

    final static Logger LOG = LoggerFactory.getLogger(AppTests.class);

    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        userRepository.deleteAll();
        postRepository.deleteAll();
        User user1 = new User("mongo");
        User user2 = new User("mongo2");
        userRepository.save(user1);
        userRepository.save(user2);
        postRepository.save(new Post("post", user1));
        postRepository.save(new Post("post2", user2));
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

    @Test
    public void testHtmlUnit() throws Exception {
        final WebClient webClient = new WebClient();
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        final HtmlPage page = webClient.getPage("http://topclub.ua/kiev/restaurants/list");

        final HtmlDivision pager = page.getFirstByXPath("//div[@class='paginate']");
        final List<?> items = page.getByXPath("//div[@id='places_list']/div[@class='item']");

        for (Object item: items) {
            HtmlDivision div = (HtmlDivision) item;
            HTMLHeadingElement title = div.getFirstByXPath("//h3");
            //LOG.info(div.getHtmlElementsByTagName("h3").getTextContent());
            LOG.info(title.getTextContent());
        }

    }
}
