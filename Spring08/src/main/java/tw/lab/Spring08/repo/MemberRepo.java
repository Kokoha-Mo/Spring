package tw.lab.Spring08.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.lab.Spring08.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmail(String email);
}
