package tw.lab.Spring09.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.lab.Spring09.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmail(String email);
}
