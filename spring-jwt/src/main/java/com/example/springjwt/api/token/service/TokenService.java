package com.example.springjwt.api.token.service;

import com.example.springjwt.api.token.dto.AccessTokenResponseDto;
import com.example.springjwt.domain.member.entitiy.Member;
import com.example.springjwt.domain.member.service.MemberService;
import com.example.springjwt.global.jwt.constant.GrantType;
import com.example.springjwt.global.jwt.service.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenService {

    private final MemberService memberService;
    private final TokenManager tokenManager;

    public AccessTokenResponseDto createAccessTokenByRefreshToken(String refreshToken) {
        Member member = memberService.findMemberByRefreshToken(refreshToken);

        Date accessTokenExpireTime = tokenManager.createAccessTokenExpireTime();
        String accessToken = tokenManager.createAccessToken(member.getId(), member.getRole(), accessTokenExpireTime);

        return AccessTokenResponseDto.builder()
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .build();
    }
}