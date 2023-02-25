package com.example.springjwt.api.member.service;

import com.example.springjwt.api.member.dto.MemberInfoResponseDto;
import com.example.springjwt.domain.member.entitiy.Member;
import com.example.springjwt.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberInfoService {

    private final MemberRepository memberRepository;


    @Transactional(readOnly = true)
    public MemberInfoResponseDto getMemberInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("엔티티가 존재하지 않음"));

        return MemberInfoResponseDto.of(member);
    }
}
