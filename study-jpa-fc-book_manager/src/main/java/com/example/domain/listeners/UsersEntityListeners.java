package com.example.domain.listeners;


import com.example.domain.Member;
import com.example.domain.MemberHistory;
import com.example.repository.MemberHistoryRepository;
import com.example.support.BeanUtils;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
public class UsersEntityListeners {

    @PostPersist
    @PostUpdate
    public void postPersistAndPostUpdate(Object o) {
        MemberHistoryRepository usersHistoryRepository = BeanUtils.getBean(MemberHistoryRepository.class);

        Member member = (Member) o;

        MemberHistory memberHistory = new MemberHistory();

        memberHistory.setName(member.getName());
        memberHistory.setEmail(member.getEmail());
        memberHistory.setCompanyAddress(member.getCompanyAddress());
        memberHistory.setHomeAddress(member.getHomeAddress());
        //memberHistory.setUserId(member.getId());
        memberHistory.setMember(member);

        usersHistoryRepository.save(memberHistory);

    }
}
