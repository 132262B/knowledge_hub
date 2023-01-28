package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

// custom 을 사용할때 뒤에 impl 을 붙혀야함.
// 붙이기 싫으면 다른 설정을 통해 변경 할 수 있음.
// 근데 관례상 쓰는게 좋다고함. , 다른 유지보수를 하는 사람들을 위해.
@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

}
