package study.querydsl.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberTeamDto> search(String username, Integer age) {
        return memberRepository.search(username, age);
    }

    public Page<MemberTeamDto> searchPageSimple(String username, Integer age, Pageable pageable) {
        return memberRepository.searchPageSimple(username, age, pageable);
    }

    public Page<MemberTeamDto> searchPageComplex(String username, Integer age, Pageable pageable) {
        return memberRepository.searchPageComplex(username, age, pageable);
    }
}

