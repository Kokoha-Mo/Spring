package com.example.spring1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring1.entity.Member;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);
}
