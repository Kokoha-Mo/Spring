package tw.lab.Spring07.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.lab.Spring07.dto.Login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/login")
    public void login(@RequestBody Login login) {
        System.out.println(login.getEmail());
        System.out.println(login.getPasswd());

    }

}
