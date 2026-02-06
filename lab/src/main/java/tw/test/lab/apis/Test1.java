package tw.test.lab.apis;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/Test1")
public class Test1 {
    @RequestMapping("/abc")
    public void test1() {
        System.out.println("Test1()");
    }
    // @GetMapping("/test1")
    // public void test1() {
    // System.out.println("Test1:test1()");
    // }
}