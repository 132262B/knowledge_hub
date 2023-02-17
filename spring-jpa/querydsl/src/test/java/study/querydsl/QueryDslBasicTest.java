package study.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.dto.QMemberDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.Team;

import javax.persistence.EntityManager;
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

    @Test
    void subQuery() {

        QMember memberSub = new QMember("memberSub");

        Member one = query
                .selectFrom(member)
                .where(member.age.eq(
                        JPAExpressions
                                .select(memberSub.age.max())
                                .from(memberSub)
                ))
                .fetchOne();

        System.out.println(one);
    }

    @Test
    void subQuery_in() {

        QMember subMember = new QMember("subMember");

        List<Member> memberList = query
                .select(member)
                .from(member)
                .where(member.age.in(JPAExpressions
                        .select(subMember.age)
                        .from(subMember)
                        .where(subMember.age.gt(10))
                ))
                .fetch();

        memberList.forEach(System.out::println);
    }

    @Test
    public void caseQuery() {
        List<Tuple> fetch = query
                .select(member.username, member.age.when(20).then("스무살").otherwise("기타"))
                .from(member)
                .fetch();

        fetch.forEach(System.out::println);
    }

    @Test
    public void caseBuilderQuery() {
        List<Tuple> fetch = query
                .select(member.username, new CaseBuilder().when(member.age.between(0, 20)).then("0~20살")
                        .when(member.age.between(21, 40)).then("21~40살")
                        .otherwise("기타"))
                .from(member)
                .fetch();

        fetch.forEach(System.out::println);
    }

    @Test
    public void constant() {
        List<Tuple> a = query
                .select(member.username, Expressions.constant("A"))
                .from(member)
                .fetch();

        a.forEach(System.out::println);
    }

    @Test
    public void concat() {
        List<String> fetch = query
                .select(member.username.prepend("_").concat("_").concat(member.age.stringValue()))
                .from(member)
                .fetch();

        fetch.forEach(System.out::println);
    }

    @Test
    void findDtoQueryProjection() {
        List<MemberDto> list = query
                .select(new QMemberDto(member.username, member.age))
                .from(member)
                .fetch();

        list.forEach(System.out::println);
    }

    @Test
    void 동적쿼리_BooleanBuilder() {
        String usernameParam = "member1";
        Integer ageParam = null;

        List<Member> result = searchMember1(usernameParam, ageParam);

        result.forEach(System.out::println);
    }

    private List<Member> searchMember1(String usernameParam, Integer ageParam) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (usernameParam != null)
            booleanBuilder.and(member.username.eq(usernameParam));

        if (ageParam != null)
            booleanBuilder.and(member.age.eq(ageParam));

        return query
                .selectFrom(member)
                .where(booleanBuilder)
                .fetch();
    }

    @Test
    void 동적쿼리_whereParam() {
        String usernameParam = null;
        Integer ageParam = null;

        List<Member> result = searchMember2(usernameParam, ageParam);

        result.forEach(System.out::println);
    }

    private List<Member> searchMember2(String usernameParam, Integer ageParam) {
        return query
                .selectFrom(member)
                //.where(usernameEq(usernameParam), ageEq(ageParam))
                .where(allEq(usernameParam, ageParam))
                .fetch();
    }

    private BooleanExpression ageEq(Integer ageParam) {
        return ageParam == null ? null : member.age.eq(ageParam);
    }

    private BooleanExpression usernameEq(String usernameParam) {
        return usernameParam == null ? null : member.username.eq(usernameParam);
    }

    private BooleanExpression allEq(String username, Integer age) {
        return usernameEq(username).and(ageEq(age));
    }

    @Test
    void 수정_삭제_배치쿼리() {
        long count = query
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(30))
                .execute();

        System.out.println(count);
    }

    @Test
    void 수정_삭제_배치쿼리_기존값연산() {
        long count = query
                .update(member)
                .set(member.age, member.age.add(1))
                .where(member.age.lt(30))
                .execute();

        System.out.println(count);
    }

    @Test
    void sqlFunction() {
        List<String> list = query
                .select(Expressions.stringTemplate(
                        "function('replace',{0},{1},{2})",
                        member.username,
                        "member",
                        "M"
                ))
                .from(member)
                .fetch();

        list.forEach(System.out::println);
    }

}
