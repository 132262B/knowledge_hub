package com.example.repository;

import com.example.domain.Users;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
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


}
