package com.example.spring1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring1.dto.User;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping("/test1")
    public void test1() {
        User user = new User();
        user.setId(1);
        user.setName("Happy");
        user.setGender(false);
        // ----buider----
        // User user = User.builder().id(1).name("Sad").gender(true).build();
        System.out.println(user.toString());
    }
}
