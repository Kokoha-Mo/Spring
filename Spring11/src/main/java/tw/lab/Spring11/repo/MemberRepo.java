package tw.lab.Spring11.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.lab.Spring11.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);

    Member findByEmail(String email);
}
