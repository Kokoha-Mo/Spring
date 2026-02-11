package tw.lab.Spring04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.lab.Spring04.entity.Member;
import tw.lab.Spring04.entity.Profile;
import tw.lab.Spring04.repo.MemberRepo;
import tw.lab.Spring04.repo.ProfileRepo;
import tw.lab.Spring04.util.BCrypt;

@Service
public class MemberService {
    @Autowired
    private MemberRepo memberRepo;
    @Autowired
    private ProfileRepo profileRepo;

    @Transactional
    public Member save(Member member, Profile profile) {

        member.setPasswd(BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
        member.setProfile(profile);

        return memberRepo.save(member);
        // profile.setMember(member);
        // profileRepo.save(profile);

    }

    @Transactional
    public Profile setProfile(Profile profile, Long memberId) {
        Member member = memberRepo.findById(memberId).orElse(null);
        if (member != null) {
            Profile p = member.getProfile();
            if (p != null) {
                profile.setId(p.getId());
            }
            member.setProfile(profile);
            Member save = memberRepo.save(member);
            return save.getProfile();
        }
        return null;
    }

    public Member findMemberById(Long id) {
        return memberRepo.findById(id).orElse(null);
    }

    @Transactional
    public boolean deletMember(Long id) {
        Member member = memberRepo.findById(id).orElse(null);
        if (member != null) {
            memberRepo.delete(member);
            return true;
        } else {
            return false;
        }
    }

}
