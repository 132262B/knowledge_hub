package com.example.repository;

import com.example.domain.Gender;
import com.example.domain.Users;
import com.example.domain.UsersHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;


@SpringBootTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersHistoryRepository usersHistoryRepository;

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
    void queryMethodTest() {
        System.out.println(usersRepository.getByEmailAndName("cal@gmail.com", "cal"));
        System.out.println(usersRepository.searchUsersByEmail("igor@gmail.com"));

        usersRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(7L)).forEach(System.out::println);

        usersRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)).forEach(System.out::println);

        usersRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)).forEach(System.out::println);

        usersRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now()).forEach(System.out::println);

        usersRepository.findByIdBetween(3L, 5L).forEach(System.out::println);

        usersRepository.findByIdIsNotNull().forEach(System.out::println);

        usersRepository.findByNameIn(Lists.newArrayList("sopia", "cal", "igor"), PageRequest.of(0, 2, Sort.by(Sort.Order.desc("name")))).forEach(System.out::println);

        usersRepository.findByNameIn(Lists.newArrayList("sopia", "cal", "igor"), PageRequest.of(1, 2, Sort.by(Sort.Order.desc("name")))).forEach(System.out::println);
    }


    @Test
    void updatableAndInsertableTest() {

        Users users = Users.builder()
                .name("jack")
                .email("jack@gmail.com")
                .build();

        // insert
        Users users2 = usersRepository.save(users);

        System.out.println(users2);

        users2.setName("jackson");

        // id값이 존재하여 update
        usersRepository.save(users2);

        usersRepository.findAll().forEach(System.out::println);
    }

    @Test
    void enumTest() {
        Users users = Users.builder()
                .name("jack")
                .email("jack@gmail.com")
                .gender(Gender.MALE)
                .build();

        usersRepository.save(users);

        usersRepository.findAll().forEach(System.out::println);

    }

    @Test
    void usersRelationTest() {
        Users user = Users.builder()
                .email("132262b@gmail.com")
                .name("junho")
                .gender(Gender.MALE)
                .build();

        usersRepository.save(user);

        user.setName("junhogu");

        usersRepository.save(user);

        user.setEmail("132262b@naver.com");

        usersRepository.save(user);

        List<UsersHistory> list = usersRepository.findById(6L)
                .orElseThrow(RuntimeException::new)
                .getUserHistories();

        list.forEach(System.out::println);
    }

    @Test
    void usersRelationTest2() {
        Users user = Users.builder()
                .email("132262b@gmail.com")
                .name("junho")
                .gender(Gender.MALE)
                .build();

        System.out.println(usersRepository.save(user));

        user.setName("junhogu");

        usersRepository.save(user);

        user.setEmail("132262b@naver.com");

        usersRepository.save(user);

        usersHistoryRepository.findAll().forEach(System.out::println);

    }

}