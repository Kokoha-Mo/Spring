package tw.lab.Spring07.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.lab.Spring07.util.JwtToken;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/test1")
    public void test1() {
        String token = JwtToken.createToken("1:lab@lab.tw");
        System.out.println(token);
    }

    @RequestMapping("/test2/{token}")
    public void test2(@PathVariable String token) {
        String subject = JwtToken.parseToken(token);
        System.out.println(subject);
    }

}
