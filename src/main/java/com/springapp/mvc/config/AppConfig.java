package com.springapp.mvc.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.net.UnknownHostException;

@Configuration
@ComponentScan(basePackages = "com.springapp.mvc")
@EnableWebMvc
@EnableMongoRepositories(basePackages = "com.springapp.mvc")
public class AppConfig extends WebMvcConfigurerAdapter{

    @Bean
    public MongoClient mongoClient() throws UnknownHostException {
        return new MongoClient("ds031651.mongolab.com", 31651);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        UserCredentials userCredentials = new UserCredentials("root", "root");
        return new MongoTemplate(mongoClient(), "spring-app", userCredentials);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    }
}
