package tw.lab.Spring06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test1")
    public void test1() {
        System.out.println("TestController:test1()");
    }

    @RequestMapping("/test2")
    public void test2() {
        System.out.println("TestController:test2()");
    }
}
