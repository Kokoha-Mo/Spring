package tw.lab.Spring09.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tw.lab.Spring09.entity.Member;
import tw.lab.Spring09.repo.MemberRepo;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberRepo memberRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepo.findByEmail(username).orElse(null);
        if (member == null)
            throw new UsernameNotFoundException("EMAIL NOT FOUND");

        return User.builder()
                .username(member.getEmail())
                .password(member.getPasswd())
                .roles(member.getRole())
                .build();
    }

}
