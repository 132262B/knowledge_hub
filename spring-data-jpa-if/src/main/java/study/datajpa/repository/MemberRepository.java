package study.datajpa.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import study.datajpa.entity.Member;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> , MemberRepositoryCustom {

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    // 기본적으로 JPA를 이용해 데이터를 조회하게되면 영속성 컨텍스트에 저장되어 관리되고,
    // 그 값을 수정한 뒤 flush()하거나 dirty checking이 발생하면 업데이트 쿼리도 발생하게 됩니다.
    // Hint를 사용하여 영속성 컨텍스트에 저장되는 것을
    // 방지해 메모리 낭비를 막고 혹시 모를 변경사항에 대해 업데이트되지 않도록 할 수 있습니다.
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true")) // (1)
    Member findMemberByUsername(String username);

    // 락 관련은 동시성 이슈 프로젝트로 공부.
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);


}