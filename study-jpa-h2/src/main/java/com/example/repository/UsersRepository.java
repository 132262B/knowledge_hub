package com.example.repository;

import com.example.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository< 1 , 2 >
// 1번은 반환할 entity 객체
// 2번은 반환할 entity 객체의 pk type
public interface UsersRepository extends JpaRepository<Users, Long> {
}
