package tw.lab.Spring04.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.lab.Spring04.entity.Member;
import tw.lab.Spring04.entity.Profile;
import tw.lab.Spring04.repo.MemberRepo;
import tw.lab.Spring04.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberService service;

    /*
     * POST /members
     * {
     * "email":"xxx",
     * "passwd":"xxx",
     * "profile":{
     * "cname":"xxx",
     * "age":xxx
     * }
     * }
     * => Member
     */
    @PostMapping("")
    public ResponseEntity<Member> addMember(@RequestBody Map<String, Object> data) {
        Member member = new Member();

        member.setEmail((String) data.get("email"));
        member.setPasswd((String) data.get("passwd"));

        Profile profile = null;
        Map<String, Object> pData = (Map<String, Object>) data.get("profile");
        if (pData != null) {
            profile = new Profile();
            profile.setCname((String) pData.get("cname"));
            profile.setAge((Integer) pData.get("age"));
        }

        Member saveMember = service.save(member, profile);
        return ResponseEntity.ok(saveMember);
    }

    @Autowired
    private MemberRepo memberRepo;

    @PutMapping("")
    @Transactional
    public ResponseEntity<Member> updateMember(@RequestBody Map<String, Object> data) {
        int id = (Integer) data.get("id");
        Member member = memberRepo.findById((long) id).orElse(null);
        if (member != null) {
            member.setEmail((String) data.get("email"));
            Member save = memberRepo.save(member);
            return ResponseEntity.ok(save);
        }
        return ResponseEntity.ok(null);

    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<Profile> udpateProfile(@PathVariable Long id, @RequestBody Map<String, Object> pData) {
        Profile profile = new Profile();
        profile.setCname((String) pData.get("cname"));
        profile.setAge((Integer) pData.get("age"));

        Profile save = service.setProfile(profile, id);

        return ResponseEntity.ok(save);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> queryMember(@PathVariable Long memberId) {
        Member member = service.findMemberById(memberId);
        return ResponseEntity.ok(member);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Boolean> deletMember(@PathVariable Long memberId) {
        boolean isSuccess = service.deletMember(memberId);
        return ResponseEntity.ok(isSuccess);
    }

}
