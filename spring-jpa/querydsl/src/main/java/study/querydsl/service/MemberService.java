package study.querydsl.service;


import lombok.RequiredArgsConstructor;
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

}
