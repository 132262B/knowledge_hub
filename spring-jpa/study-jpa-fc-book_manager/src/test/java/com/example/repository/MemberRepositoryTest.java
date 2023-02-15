package com.example.repository;

import com.example.domain.Address;
import com.example.domain.Gender;
import com.example.domain.Member;
import com.example.domain.MemberHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberHistoryRepository memberHistoryRepository;

    @Test
    void crud() {
        // 정렬
        // List<Member> users = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));

        // Lists.newArrayList() 는 assertj 에서 제공해주는 테스트용 함수.
        List<Member> users = memberRepository.findAllById(Lists.newArrayList(1L, 2L, 3L));

        users.forEach(System.out::println);
    }

    @Test
    void crud_sort() {
        // 정렬
        // name 컬럼 기준 역순 조회
        List<Member> users = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));

        users.forEach(System.out::println);
    }

    @Test
    void crud_findAllById_in() {
        //in 조회
        // Lists.newArrayList() 는 assertj 에서 제공해주는 테스트용 함수.
        List<Member> users = memberRepository.findAllById(Lists.newArrayList(1L, 2L, 3L));

        users.forEach(System.out::println);
    }

    @Test
    void crud_saveAll() {
        Member member1 = Member.builder().email("이것은 이메일이오1111111").name("이것은 이름이오11111111").build();

        Member member2 = Member.builder().email("이것은 이메일이오22222222").name("이것은 이름이오2222222").build();

        memberRepository.saveAll(Lists.newArrayList(member1, member2));

    }

    @Test
    void crud_findById() {
        Optional<Member> users = memberRepository.findById(234L);

        // Optional.empty
        System.out.println(users);
    }

    @Test
    void crud_orElse() {
        Member member = memberRepository.findById(234L).orElse(null);

        System.out.println(member);
    }

    @Test
    void crud_flush() {

        memberRepository.save(Member.builder().email("이것은 이메일이오1111111").name("이것은 이름이오11111111").build());

        // db 반영시점을 조작
        //memberRepository.flush();

        memberRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud_count() {
        long count = memberRepository.count();

        System.out.println(count);
    }

    @Test
    void crud_existsById() {
        boolean status = memberRepository.existsById(1L);

        System.out.println(status);
    }

    @Test
    void crud_deleteAll_findById() {
        memberRepository.deleteAll(memberRepository.findAllById(Lists.newArrayList(1L, 3L)));
        memberRepository.findAll().forEach(System.out::println);
    }


    @Test
    void crud_page() {
        Page<Member> users = memberRepository.findAll(PageRequest.of(0, 3));

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

        Example<Member> example = Example.of(Member.builder().name("ig").email("gmail.com").build(), matcher);

        memberRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void queryMethodTest() {
        System.out.println(memberRepository.getByEmailAndName("cal@gmail.com", "cal"));
        System.out.println(memberRepository.searchUsersByEmail("igor@gmail.com"));

        memberRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(7L)).forEach(System.out::println);

        memberRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)).forEach(System.out::println);

        memberRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)).forEach(System.out::println);

        memberRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now()).forEach(System.out::println);

        memberRepository.findByIdBetween(3L, 5L).forEach(System.out::println);

        memberRepository.findByIdIsNotNull().forEach(System.out::println);

        memberRepository.findByNameIn(Lists.newArrayList("sopia", "cal", "igor"), PageRequest.of(0, 2, Sort.by(Sort.Order.desc("name")))).forEach(System.out::println);

        memberRepository.findByNameIn(Lists.newArrayList("sopia", "cal", "igor"), PageRequest.of(1, 2, Sort.by(Sort.Order.desc("name")))).forEach(System.out::println);
    }


    @Test
    void updatableAndInsertableTest() {

        Member member = Member.builder()
                .name("jack")
                .email("jack@gmail.com")
                .build();

        // insert
        Member member2 = memberRepository.save(member);

        System.out.println(member2);

        member2.setName("jackson");

        // id값이 존재하여 update
        memberRepository.save(member2);

        memberRepository.findAll().forEach(System.out::println);
    }

    @Test
    void enumTest() {
        Member member = Member.builder()
                .name("jack")
                .email("jack@gmail.com")
                .gender(Gender.MALE)
                .build();

        memberRepository.save(member);

        memberRepository.findAll().forEach(System.out::println);

    }

    @Test
    void usersRelationTest() {
        Member user = Member.builder()
                .email("132262b@gmail.com")
                .name("junho")
                .gender(Gender.MALE)
                .build();

        memberRepository.save(user);

        user.setName("junhogu");

        memberRepository.save(user);

        user.setEmail("132262b@naver.com");

        memberRepository.save(user);

        List<MemberHistory> list = memberRepository.findById(6L)
                .orElseThrow(RuntimeException::new)
                .getMemberHistories();

        list.forEach(System.out::println);
    }

    @Test
    void usersRelationTest2() {
        Member user = Member.builder()
                .email("132262b@gmail.com")
                .name("junho")
                .gender(Gender.MALE)
                .build();

        System.out.println(memberRepository.save(user));

        user.setName("junhogu");

        memberRepository.save(user);

        user.setEmail("132262b@naver.com");

        memberRepository.save(user);

        memberHistoryRepository.findAll().forEach(System.out::println);

    }

    @Test
    void embedTest() {

        Member member = new Member();

        member.setName("홍길동");
        member.setHomeAddress(new Address("서울시","강남구","테헤란로234","12345"));
        member.setCompanyAddress(new Address("부산","강서구","준호로","2352"));

        memberRepository.save(member);

        memberRepository.findAll().forEach(System.out::println);


        memberHistoryRepository.findAll().forEach(System.out::println);
    }

}