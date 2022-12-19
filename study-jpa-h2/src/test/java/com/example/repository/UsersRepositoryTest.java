package com.example.repository;

import com.example.domain.Users;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@SpringBootTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void crud() {
        // 정렬
        // List<Users> users = usersRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));

        // Lists.newArrayList() 는 assertj 에서 제공해주는 테스트용 함수.
        List<Users> users = usersRepository.findAllById(Lists.newArrayList(1L,2L,3L));

        users.forEach(System.out::println);
    }

    @Test
    void crud_sort() {
        // 정렬
        // name 컬럼 기준 역순 조회
        List<Users> users = usersRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));

        users.forEach(System.out::println);
    }

    @Test
    void crud_findAllById_in() {
        //in 조회
        // Lists.newArrayList() 는 assertj 에서 제공해주는 테스트용 함수.
        List<Users> users = usersRepository.findAllById(Lists.newArrayList(1L,2L,3L));

        users.forEach(System.out::println);
    }

    @Test
    void crud_saveAll() {
        Users users1 = Users.builder()
                .email("이것은 이메일이오1111111")
                .name("이것은 이름이오11111111")
                .build();

        Users users2 = Users.builder()
                .email("이것은 이메일이오22222222")
                .name("이것은 이름이오2222222")
                .build();

        usersRepository.saveAll(Lists.newArrayList(users1,users2));

    }

    @Test
    //@Transactional
    void crud_getOne() {
        // org.hibernate.LazyInitializationException: could not initialize proxy 발생

        Users users =  usersRepository.getOne(1L);
        System.out.println(users);
    }

    @Test
    void crud_findById() {
        Optional<Users> users =  usersRepository.findById(234L);
        System.out.println(users);
    }

}