package tw.lab.Spring05.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.lab.Spring05.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
