package study.querydsl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.querydsl.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> search(String username, Integer age);

    Page<MemberTeamDto> searchPageSimple(String username, Integer age, Pageable pageable);

    Page<MemberTeamDto> searchPageComplex(String username, Integer age, Pageable pageable);


}
