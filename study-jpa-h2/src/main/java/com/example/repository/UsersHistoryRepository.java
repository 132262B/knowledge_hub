package com.example.repository;

import com.example.domain.UsersHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersHistoryRepository extends JpaRepository<UsersHistory, Long> {

    List<UsersHistory> findByUserId(Long usersId);

}