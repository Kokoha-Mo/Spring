package tw.lab.Spring05.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import tw.lab.Spring05.dto.MemberForm;
import tw.lab.Spring05.entity.Member;
import tw.lab.Spring05.service.MemberService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(
            @ModelAttribute MemberForm memberForm,
            Model model) {
        try {
            memberService.register(memberForm);
            return "redirect:/member/login";
        } catch (Exception e) {
            System.out.println(e);
            model.addAttribute("error", "帳號已註冊或其他錯誤");
            return "register";
        }
        // return "login";

    }

    @PostMapping("/register2")
    public String doRegister2(
            @RequestParam String email,
            @RequestParam String passwd,
            @RequestParam String name,
            @RequestParam MultipartFile iconFile,
            Model model) {
        return "test";

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(
            @RequestParam String email,
            @RequestParam String passwd,
            HttpSession session) {
        Member member = memberService.login(email, passwd);
        if (member != null) {
            session.setAttribute("member", member);
            return "redirect:/member/home";
        }
        return "login";
    }

    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
        Object obj = session.getAttribute("member");
        if (obj == null)
            return "redirect:/member/login";
        Member member = (Member) obj;
        model.addAttribute("member", member);
        model.addAttribute("icon", "data:image/*;base64," + Base64.getEncoder().encodeToString(member.getIcon()));

        return "home";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/member/login";
    }

}
