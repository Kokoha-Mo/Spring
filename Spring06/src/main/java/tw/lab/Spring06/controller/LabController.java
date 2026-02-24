package tw.lab.Spring06.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.lab.Spring06.annotation.LabAOP;
import tw.lab.Spring06.dto.Register;

@RestController
@RequestMapping("/lab")
public class LabController {

    @RequestMapping("/test1")
    public void test11(
            @RequestParam String name) {
        System.out.println(name);
    }

    @LabAOP
    @RequestMapping("/test2")
    public void test22() {
        System.out.println("test22");
    }

    @RequestMapping("/test3")
    public void test3(@RequestBody Register register) {
        System.out.println(register.getAccount());
    }
}
