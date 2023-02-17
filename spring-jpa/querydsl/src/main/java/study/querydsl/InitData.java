package study.querydsl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void init() {
        initMemberService.initData();
    }

    @Component
    static class InitMemberService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void initData() {
            Team team1 = new Team("팀1");
            Team team2 = new Team("팀2");

            em.persist(team1);
            em.persist(team2);

            for(int i = 0; i<100; i++) {
                Team team = (i%2 == 0) ? team1: team2;
                Member member = new Member("회원"+i, 20+i, team);
                em.persist(member);
            }
        }
    }

}
