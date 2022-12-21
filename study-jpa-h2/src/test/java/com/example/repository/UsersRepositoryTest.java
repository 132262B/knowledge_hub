package com.example.repository;

import com.example.domain.Users;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;


@SpringBootTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void crud() {
        // 정렬
        // List<Users> users = usersRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));

        // Lists.newArrayList() 는 assertj 에서 제공해주는 테스트용 함수.
        List<Users> users = usersRepository.findAllById(Lists.newArrayList(1L, 2L, 3L));

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
        List<Users> users = usersRepository.findAllById(Lists.newArrayList(1L, 2L, 3L));

        users.forEach(System.out::println);
    }

    @Test
    void crud_saveAll() {
        Users users1 = Users.builder().email("이것은 이메일이오1111111").name("이것은 이름이오11111111").build();

        Users users2 = Users.builder().email("이것은 이메일이오22222222").name("이것은 이름이오2222222").build();

        usersRepository.saveAll(Lists.newArrayList(users1, users2));

    }

    @Test
    @Transactional
    void crud_getOne() {
        // org.hibernate.LazyInitializationException: could not initialize proxy 발생

        Users users = usersRepository.getOne(1L);
        System.out.println(users);
    }

    @Test
    void crud_findById() {
        Optional<Users> users = usersRepository.findById(234L);

        // Optional.empty
        System.out.println(users);
    }

    @Test
    void crud_orElse() {
        Users users = usersRepository.findById(234L).orElse(null);

        System.out.println(users);
    }

    @Test
    void crud_flush() {

        usersRepository.save(Users.builder().email("이것은 이메일이오1111111").name("이것은 이름이오11111111").build());

        // db 반영시점을 조작
        //usersRepository.flush();

        usersRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud_count() {
        long count = usersRepository.count();

        System.out.println(count);
    }

    @Test
    void crud_existsById() {
        boolean status = usersRepository.existsById(1L);

        System.out.println(status);
    }

    @Test
    void crud_delete() {
        usersRepository.delete(usersRepository.findById(1L).orElseThrow(RuntimeException::new));
    }

    @Test
    void crud_deleteById() {
        usersRepository.deleteById(1L);
        usersRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud_deleteAll() {
        usersRepository.deleteAll();
        usersRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud_deleteAll_findById() {
        usersRepository.deleteAll(usersRepository.findAllById(Lists.newArrayList(1L, 3L)));
        usersRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud_deleteInBatch() {
        usersRepository.deleteInBatch(usersRepository.findAllById(Lists.newArrayList(1L, 3L)));
        usersRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud_deleteAllInBatch() {
        usersRepository.deleteAllInBatch();
        usersRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud_deleteAllInBatch_findAllById() {
        usersRepository.deleteAllInBatch(usersRepository.findAllById(Lists.newArrayList(1L, 3L)));
        usersRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud_page() {
        Page<Users> users = usersRepository.findAll(PageRequest.of(0, 3));

        System.out.println("page : " + users);
        System.out.println("totalElements : " + users.getTotalElements());
        System.out.println("totalPages : " + users.getTotalPages());
        System.out.println("numberOfElements : " + users.getNumberOfElements());
        System.out.println("sort : " + users.getSort());
        System.out.println("size : " + users.getSize());

        users.getContent().forEach(System.out::println);
    }

    @Test
    void crud_Example() {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", endsWith());

        Example<Users> example = Example.of(Users.builder().name("ig").email("gmail.com").build(), matcher);

        usersRepository.findAll(example).forEach(System.out::println);
    }


    @Test
    void select() {
        //usersRepository.findByName("cal").forEach(System.out::println);

        System.out.println(usersRepository.findByName("cal"));
    }

    @Test
    void select_getByEmailAndName() {
        //System.out.println(usersRepository.getByEmailAndName("cal@gmail.com","cal"));
        System.out.println(usersRepository.searchUsersByEmail("igor@gmail.com"));


    }


}