package study.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.repository.MemberRepository;
import study.datajpa.repository.TeamRepository;

import javax.persistence.EntityManager;

import java.util.List;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void memberEntityTest() {
        // given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 20, teamA);
        Member member2 = new Member("member2", 21, teamA);
        Member member3 = new Member("member3", 24, teamB);
        Member member4 = new Member("member4", 22, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();
        // when

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        members.forEach(o -> {
            System.out.println(o.toString());
            System.out.println(o.getTeam().toString());
        });

        // then
    }

    @Test
    void memberEntityGraphTest() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 20, teamA);
        Member member2 = new Member("member2", 21, teamA);
        Member member3 = new Member("member3", 24, teamB);
        Member member4 = new Member("member4", 22, teamB);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);

        // when
        memberRepository.findAll().forEach(o -> {
            System.out.println(o.toString());
            System.out.println(o.getTeam());
        });

        // then
    }
    
    @Test
    void Lock() {
        // given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 20, teamA);
        Member member2 = new Member("member2", 21, teamA);
        Member member3 = new Member("member3", 24, teamB);
        Member member4 = new Member("member4", 22, teamB);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);

        // when
        memberRepository.findLockByUsername("member1").forEach(System.out::println);
    }

    @Test
    void customMemberRepositoryTest() {
        // given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 20, teamA);
        Member member2 = new Member("member2", 21, teamA);
        Member member3 = new Member("member3", 24, teamB);
        Member member4 = new Member("member4", 22, teamB);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);

        // when

        memberRepository.findMemberCustom().forEach(System.out::println);
        
        // then
    }

}