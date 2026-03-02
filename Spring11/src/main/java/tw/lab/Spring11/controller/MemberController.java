package tw.lab.Spring11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.lab.Spring11.dto.Register;
import tw.lab.Spring11.entity.Member;
import tw.lab.Spring11.service.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Register login) {
        try {
            Member member = memberService.register(login.email(), login.passwd(), login.name());
            return ResponseEntity.ok(member);
        } catch (Exception e) {
            return ResponseEntity.ok("error:" + e.getMessage());
        }
    }
}
