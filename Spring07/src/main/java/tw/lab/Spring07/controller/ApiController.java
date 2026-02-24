package tw.lab.Spring07.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.lab.Spring07.annotation.CheckJwt;
import tw.lab.Spring07.dto.Login;
import tw.lab.Spring07.util.JwtToken;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Login login) {
        // System.out.println(login.getEmail());
        // System.out.println(login.getPasswd());
        if (login.getEmail().equals("lab@lab.tw") && login.getPasswd().equals("12345678")) {
            String data = String.format("%d:%s", 123, login.getEmail());
            String token = JwtToken.createToken(data);

            Map<String, Object> resp = Map.of(
                    "success", true,
                    "token", token);
            return ResponseEntity.ok(resp);
        } else {
            Map<String, Object> resp = Map.of(
                    "success", false);
            return ResponseEntity.ok(resp);
        }

    }

    @CheckJwt
    @GetMapping("/main")
    public ResponseEntity<Map<String, Object>> main() {
        System.out.println("Data");
        return ResponseEntity.ok(Map.of("success", true, "data", "MemberOnly"));
    }

}
