package study.querydsl.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    private EntityManager em;

    @Test
    public void testEntity() {

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        Member member1 = new Member("member1", 20, teamA);
        Member member2 = new Member("member2", 21, teamA);
        Member member3 = new Member("member3", 22, teamB);
        Member member4 = new Member("member4", 23, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m join fetch m.team t", Member.class).getResultList();
        members.forEach((o) -> System.out.println(o.toString() + o.getTeam()));

        List<Team> teams = em.createQuery("select t from Team t  join fetch t.members", Team.class).getResultList();
        teams.forEach((o) -> System.out.println(o.toString() + o.getMembers()));


    }

}