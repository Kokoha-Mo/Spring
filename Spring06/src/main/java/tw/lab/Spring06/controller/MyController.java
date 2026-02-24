package tw.lab.Spring06.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
public class MyController {

    @RequestMapping("/test1")
    public void test1() {
        System.out.println("myController:test1()");
    }
}
