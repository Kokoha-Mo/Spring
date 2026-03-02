package tw.lab.Spring11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.lab.Spring11.entity.Member;
import tw.lab.Spring11.repo.MemberRepo;
import tw.lab.Spring11.util.BCrypt;

@Service
public class MemberService {

    @Autowired
    private MemberRepo memberRepo;

    public Member register(String email, String passswd, String name) {
        if (memberRepo.existsByEmail(email))
            throw new IllegalArgumentException("email exist");

        Member member = new Member();
        member.setEmail(email);
        member.setPasswd(BCrypt.hashpw(passswd, BCrypt.gensalt()));
        member.setName(name);

        Member savedMember = memberRepo.save(member);
        return savedMember;
    }
}
