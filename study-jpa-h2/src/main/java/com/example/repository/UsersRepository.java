package com.example.repository;

import com.example.domain.Users;
import lombok.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// JpaRepository< 1 , 2 >
// 1번은 반환할 entity 객체
// 2번은 반환할 entity 객체의 pk type
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByName(String name);

    Users findByEmail(String name);
    Users getByEmailAndName(@NonNull String email, @NonNull String name);

    Users searchUsersByEmail(@NonNull String email);

    List<Users> findByCreatedAtAfter(LocalDateTime createdAt);

    // 크다
    List<Users> findByCreatedAtGreaterThan(LocalDateTime yesterday);

    // 크거나 같다
    List<Users> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);

    // between 1
    List<Users> findByCreatedAtBetween(LocalDateTime createdAt, LocalDateTime createdAt2);

    // between2
    List<Users> findByIdBetween(Long id1, Long id2);

    List<Users> findByIdIsNotNull();

    List<Users> findByNameIn(List<String> names, PageRequest name);
}
