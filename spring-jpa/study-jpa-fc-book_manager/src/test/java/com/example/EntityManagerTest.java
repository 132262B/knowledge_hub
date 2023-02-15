package com.example;


import com.example.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
//@Transactional
public class EntityManagerTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void entityMangerTest() {
        System.out.println(
                entityManager.createQuery("select m from Member m").getResultList());
    }

    @Test
    void cacheFindTest() {
        //System.out.println(memberRepository.findByEmail("igor@gmail.com"));
        //System.out.println(memberRepository.findByEmail("igor@gmail.com"));
        //System.out.println(memberRepository.findByEmail("igor@gmail.com"));

        System.out.println(memberRepository.findById(1L).get());
        System.out.println(memberRepository.findById(1L).get());
        System.out.println(memberRepository.findById(1L).get());
    }
}
