package com.example.spring1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring1.entity.Member;
import com.example.spring1.service.MemberService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService service;

    /*
     * request: /exist?email=xxx
     * response: true/false
     */
    @GetMapping("/exist")
    public ResponseEntity<Boolean> checkEamil(@RequestParam String email) {
        boolean isExist = service.checkEamil(email);
        return ResponseEntity.ok(isExist);
    }

    /*
     * request: Member => {}
     * response: {"success":true/false}
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Boolean>> register(@RequestBody Member member) {
        boolean isSuccess = service.register(member);

        Map<String, Boolean> map = Map.of("success", isSuccess);

        return ResponseEntity.ok(map);
    }

    /*
     * request: {"email":"xxx","passwd":"xxx"}
     * response: {"success":true/false}
     */
    @PostMapping("/loginV2")
    public ResponseEntity<Map<String, Boolean>> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String passwd = body.get("passwd");

        boolean isSuccess = service.loginV2(email, passwd);
        Map<String, Boolean> map = Map.of("success", isSuccess);
        // System.out.println(email + ":" + passwd);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/loginV3")
    public ResponseEntity<Map<String, Boolean>> login(
            @RequestBody Map<String, String> body,
            HttpSession session) {

        String email = body.get("email");
        String passwd = body.get("passwd");

        Member member = service.loginV3(email, passwd);
        Map<String, Boolean> map;
        if (member != null) {
            session.setAttribute("member", member);
            map = Map.of("success", true);
        } else {
            session.invalidate();
            map = Map.of("success", false);
        }
        // System.out.println(email + ":" + passwd);
        return ResponseEntity.ok(map);
    }

    @Autowired
    @Qualifier("companyName")
    private String companyName;

    @Value("${company.tel}")
    private String companyTel;

    @PostMapping("/status")
    public ResponseEntity<Map<String, Object>> status(HttpSession session) {
        Object member = session.getAttribute("member");
        Map<String, Object> map = new HashMap<>();
        map.put("success", member != null);
        map.put("member", member);
        map.put("companyName", companyName);
        map.put("companyTel", companyTel);

        return ResponseEntity.ok(map);
    }

    @RequestMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpSession session) {
        session.invalidate();
        Map<String, String> map = new HashMap<>();
        map.put("success", "ok");

        return ResponseEntity.ok(map);
    }

}
