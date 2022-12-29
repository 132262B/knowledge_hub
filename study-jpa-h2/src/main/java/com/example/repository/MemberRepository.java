package com.example.repository;

import com.example.domain.Member;
import lombok.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// JpaRepository< 1 , 2 >
// 1번은 반환할 entity 객체
// 2번은 반환할 entity 객체의 pk type
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);

    Member findByEmail(String name);
    Member getByEmailAndName(@NonNull String email, @NonNull String name);

    Member searchUsersByEmail(@NonNull String email);

    List<Member> findByCreatedAtAfter(LocalDateTime createdAt);

    // 크다
    List<Member> findByCreatedAtGreaterThan(LocalDateTime yesterday);

    // 크거나 같다
    List<Member> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);

    // between 1
    List<Member> findByCreatedAtBetween(LocalDateTime createdAt, LocalDateTime createdAt2);

    // between2
    List<Member> findByIdBetween(Long id1, Long id2);

    List<Member> findByIdIsNotNull();

    List<Member> findByNameIn(List<String> names, PageRequest name);
}
