package study.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.QTeam;
import study.querydsl.entity.Team;

import javax.persistence.EntityManager;
import java.lang.reflect.WildcardType;
import java.sql.SQLOutput;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QMember.*;
import static study.querydsl.entity.QTeam.*;

@SpringBootTest
@Transactional
public class QueryDslBasicTest {

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
    public void startJPQL() {
        List<Member> members = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getResultList();

        members.forEach((o) -> System.out.println(o.toString() + o.getTeam()));

        assertThat(members.get(0).getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl() {
        Member result = query
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        assertThat(result.getUsername()).isEqualTo("member1");
    }

    @Test
    void search() {
        Member result = query
                .selectFrom(member)
                .where(member.username.eq("member1")
                        .and(member.age.eq(20))
                        .and(member.age.between(10, 30))
                )
                .fetchOne();

        assertThat(result.getUsername()).isEqualTo("member1");
        assertThat(result.getAge()).isEqualTo(20);
    }

    @Test
    void searchAnd() {
        Member result = query
                .selectFrom(member)
                .where(member.username.eq("member1")
                        , member.age.eq(20)
                        , member.age.between(10, 30)
                )
                .fetchOne();

        assertThat(result.getUsername()).isEqualTo("member1");
        assertThat(result.getAge()).isEqualTo(20);
    }

    @Test
    void selectFetch() {
        // List조회
        List<Member> fetch = query.selectFrom(member).fetch();

        // 단건조회
        Member member1 = query.selectFrom(member).limit(1L).fetchOne();
    }

    @Test
    public void sort() {
        List<Member> list = query
                .selectFrom(member)
                .orderBy(member.age.desc(), member.username.asc().nullsFirst())
                .fetch();

        list.forEach(System.out::println);
    }

    @Test
    void paging() {
        List<Member> list = query
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetch();

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void aggregation() {
        List<Tuple> list = query
                .select(
                        member.count()
                        , member.age.sum()
                        , member.age.avg()
                        , member.age.max()
                        , member.age.min()
                )
                .from(member)
                .fetch();

        Tuple tuple = list.get(0);
        assertThat(tuple.get(member.count())).isEqualTo(7L);
    }

    @Test
    void group() {
        List<Tuple> list = query
                .select(team.name,
                        member.age.avg(),
                        member.age.sum()
                )
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();

        list.forEach(System.out::println);
    }

    @Test
    void join() {
        List<Member> result = query
                .select(member)
                .from(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("member1", "member2");
    }

    @Test
    void theta_join() {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));

        List<Member> list = query
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();

        list.forEach(System.out::println);
    }

    @Test
    public void join_on_filtering() {
        List<Tuple> list = query
                .select(member, team)
                .from(member)
                .innerJoin(member.team, team)
                .on(team.name.eq("teamA"))
                .fetch();

        list.forEach(System.out::println);
    }

    @Test
    public void fetchJoinNo() {
        em.flush();
        em.clear();

        Member findMember = query
                .select(member)
                .from(member)
                .join(member.team, team)
                .where(member.username.eq("member1"))
                .fetchOne();

        System.out.println(findMember.getTeam());
    }


    @Test
    public void fetchJoinUse() {
        em.flush();
        em.clear();

        Member findMember = query
                .select(member)
                .from(member)
                .join(member.team, team).fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();

        System.out.println(findMember.getTeam());
    }

}
