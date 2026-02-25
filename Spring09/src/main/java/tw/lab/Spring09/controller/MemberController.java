package tw.lab.Spring09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class MemberController {

    @RequestMapping("/test1")
    public String test1() {
        return "test1";
    }
}
