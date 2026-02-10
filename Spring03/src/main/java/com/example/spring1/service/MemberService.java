package com.example.spring1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.spring1.entity.Member;
import com.example.spring1.repository.MemberRepository;
import com.example.spring1.util.BCrypt;

@Service
public class MemberService {
    @Autowired
    private MemberRepository repository;

    public boolean checkEamil(String email) {
        return repository.existsByEmail(email);
    }

    public boolean register(Member member) {
        if (repository.existsByEmail(member.getEmail())) {
            return false;
        } else {
            member.setPasswd(BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
            Member m = repository.save(member);
            System.out.println(m.getId());
            return m != null;
        }
    }

    public boolean login(String email, String passwd) {
        Member member = repository.findByEmail(email).orElse(null);
        if (member != null & BCrypt.checkpw(passwd, member.getPasswd())) {
            return true;
        }
        return false;
    }

    public boolean loginV2(String email, String passwd) {
        Member member = new Member();
        member.setEmail(email);
        Example<Member> ex = Example.of(member);

        if (repository.exists(ex)) {
            List<Member> members = repository.findAll(ex);
            // System.out.println(member instanceof LinkedList<Map<String,String>>);
            Member dbMember = members.get(0);
            if (BCrypt.checkpw(passwd, dbMember.getPasswd())) {
                return true;
            }
        }

        return false;
    }

    public Member loginV3(String email, String passwd) {
        Member member = repository.findByEmail(email).orElse(null);
        if (member != null & BCrypt.checkpw(passwd, member.getPasswd())) {
            return member;
        }
        return null;
    }
}
