package com.example.springjwt.api.logout.service;


import com.example.springjwt.domain.member.entitiy.Member;
import com.example.springjwt.domain.member.service.MemberService;
import com.example.springjwt.global.jwt.constant.TokenType;
import com.example.springjwt.global.jwt.service.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class LogoutService {

    private final MemberService memberService;
    private final TokenManager tokenManager;

    public void logout(String accessToken) {

        // 1. 토큰 검증
        tokenManager.validateToken(accessToken);

        // 2. 토큰 타입 확인
        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        String tokenType = tokenClaims.getSubject();
        if(!TokenType.isAccessToken(tokenType)) {
            throw new RuntimeException("access token이 아님");
        }

        // 3. refresh token 만료 처리
        Long memberId = Long.valueOf((Integer)tokenClaims.get("memberId"));
        Member member = memberService.findMemberByMemberId(memberId);
        member.expireRefreshToken(LocalDateTime.now());
    }

}
