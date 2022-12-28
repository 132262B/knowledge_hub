package com.example.domain.listeners;


import com.example.domain.Users;
import com.example.domain.UsersHistory;
import com.example.repository.UsersHistoryRepository;
import com.example.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
public class UsersEntityListeners {

    @PostPersist
    @PostUpdate
    public void postPersistAndPostUpdate(Object o) {
        UsersHistoryRepository usersHistoryRepository = BeanUtils.getBean(UsersHistoryRepository.class);

        Users users = (Users) o;

        UsersHistory usersHistory = new UsersHistory();

        usersHistory.setName(users.getName());
        usersHistory.setEmail(users.getEmail());
        usersHistory.setUserId(users.getId());
        usersHistory.setUsers(users);

        usersHistoryRepository.save(usersHistory);

    }
}
