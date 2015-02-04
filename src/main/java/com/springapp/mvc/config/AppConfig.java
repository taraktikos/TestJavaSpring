package com.springapp.mvc.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import java.net.UnknownHostException;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@EnableHypermediaSupport(type = {EnableHypermediaSupport.HypermediaType.HAL})
@Import(RepositoryRestMvcConfiguration.class)
@EnableMongoRepositories(basePackages = "com.springapp.mvc")
@ComponentScan(basePackages = "com.springapp.mvc")
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

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaTypes.HAL_JSON);
    }
}
