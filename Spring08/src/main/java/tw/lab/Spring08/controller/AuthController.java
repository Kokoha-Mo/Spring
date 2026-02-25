package tw.lab.Spring08.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwt;
import tw.lab.Spring08.dto.Login;
import tw.lab.Spring08.entity.Member;
import tw.lab.Spring08.repo.MemberRepo;
import tw.lab.Spring08.response.LoginResponse;
import tw.lab.Spring08.util.BCrypt;
import tw.lab.Spring08.util.JwtToken;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private MemberRepo memberRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        Member member = memberRepo.findByEmail(login.getEmail()).orElse(null);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("帳號錯誤");
        }
        if (BCrypt.checkpw(login.getPasswd(), member.getPasswd())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密碼錯誤");
        }
        String token = JwtToken.createToken(member.getEmail());

        return ResponseEntity.ok(new LoginResponse(token, member.getEmail()));
    }
}
