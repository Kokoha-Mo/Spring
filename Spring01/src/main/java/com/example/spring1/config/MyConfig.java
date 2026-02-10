package com.example.spring1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.spring1.dto.User;

@Configuration
public class MyConfig {
    public MyConfig() {
        System.out.println("MyConfig()");
    }

    public void m1() {
        System.out.println("Myconfig:m1()");
    }

    @Bean
    public User m2() {
        System.out.println("Myconfig:m2()");
        User user = new User();
        user.setName("Alex");
        return user;
    }

    @Bean
    public RestTemplate getRestTemplete() {
        return new RestTemplate();
    }
}
