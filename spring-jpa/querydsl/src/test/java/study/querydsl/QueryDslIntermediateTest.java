package study.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import javax.persistence.EntityManager;

import java.util.List;

import static study.querydsl.entity.QMember.member;

@SpringBootTest
@Transactional
public class QueryDslIntermediateTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private JPAQueryFactory query;


    @BeforeEach
    public void beforeEach() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        Member member1 = new Member("member1", 20, teamA);
        Member member2 = new Member("member2", 21, teamA);
        Member member3 = new Member("member3", 22, teamB);
        Member member4 = new Member("member4", 23, teamB);
        Member member5 = new Member("member5", 27, teamB);
        Member member6 = new Member("member6", 29, teamB);
        Member member7 = new Member(null, 42, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        em.persist(member5);
        em.persist(member6);
        em.persist(member7);

        em.flush();
        em.clear();
    }

    @Test
    void simpleProjection() {
        List<String> strings = query
                .select(member.username)
                .from(member)
                .fetch();

        strings.forEach(System.out::println);
    }

    @Test
    void tupleProjection() {
        List<Tuple> tuples = query
                .select(member.username, member.age)
                .from(member)
                .fetch();

        tuples.forEach(System.out::println);
    }

    @Test
    void findDtoByJPQL() {
        List<MemberDto> resultList = em
                .createQuery("select new study.querydsl.dto.MemberDto(m.username, m.age) from Member m", MemberDto.class)
                .getResultList();

        resultList.forEach(System.out::println);
    }

    @Test
    void findDtoBySetter() {
        List<MemberDto> fetch = query
                .select(Projections.bean(MemberDto.class, member.username,
                        member.age)
                )
                .from(member)
                .fetch();

        fetch.forEach(System.out::println);
    }

    @Test
    void findDtoByConstructor() {
        List<MemberDto> fetch = query
                .select(Projections.constructor(MemberDto.class,
                        member.username,
                        member.age)
                )
                .from(member)
                .fetch();

        fetch.forEach(System.out::println);
    }

    @Test
    void findDtoByFields() {
        List<MemberDto> fetch = query
                .select(Projections.fields(MemberDto.class,
                        member.username.as("name"),
                        member.age)
                )
                .from(member)
                .fetch();

        fetch.forEach(System.out::println);
    }
}
