package tw.lab.Spring05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.lab.Spring05.dto.MemberForm;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "register";
    }
}
