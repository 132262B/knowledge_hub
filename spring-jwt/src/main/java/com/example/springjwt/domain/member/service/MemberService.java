package com.example.springjwt.domain.member.service;

import com.example.springjwt.domain.member.entitiy.Member;
import com.example.springjwt.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member registerMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());

        if(optionalMember.isPresent()) {
            throw new RuntimeException("이메일 중복됨");
        }
    }

    @Transactional(readOnly = true)
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Member findMemberByRefreshToken(String refreshToken) {
        Member member = memberRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("해당 토큰의 유저가 존재하지 않음"));
        LocalDateTime tokenExpirationTime = member.getTokenExpirationTime();
        if(tokenExpirationTime.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("해당 refresh token은 만료됐습니다.");
        }
        return member;
    }

    public Member findMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("맴버가 존재하지 않음"));
    }
}
