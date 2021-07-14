package com.library.authentication;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

/**
 *
 * @author Pouyeh
 */
@EnableAutoConfiguration
@SpringBootApplication
// @EntityScan(basePackages = {"com.library.authentication.model"})
// @EnableJpaRepositories("com.library.authentication.repository")
public class AuthenticationApplication {
    
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

}
