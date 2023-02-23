package com.example.springjwt.api.login.service;

import com.example.springjwt.api.login.dto.OauthLoginDto;
import com.example.springjwt.domain.member.constant.MemberType;
import com.example.springjwt.domain.member.constant.Role;
import com.example.springjwt.domain.member.entitiy.Member;
import com.example.springjwt.domain.member.service.MemberService;
import com.example.springjwt.external.oauth.model.OAuthAttributes;
import com.example.springjwt.external.oauth.service.SocialLoginApiService;
import com.example.springjwt.external.oauth.service.SocialLoginApiServiceFactory;
import com.example.springjwt.global.jwt.dto.JwtTokenDto;
import com.example.springjwt.global.jwt.service.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OauthLoginService {

    private final MemberService memberService;

    private final TokenManager tokenManager;

    public OauthLoginDto.Response oauthLogin(String accessToken, MemberType memberType) {

        SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(memberType);
        OAuthAttributes userInfo = socialLoginApiService.getUserInfo(accessToken);

        log.info("userInfo");
        log.info(userInfo.toString());

        JwtTokenDto jwtTokenDto;
        Optional<Member> optionalMember = memberService.findMemberByEmail(userInfo.getEmail());
        Member member;
        if(optionalMember.isEmpty()) {
            // 신규회원
            member = userInfo.toMemberEntity(memberType, Role.USER);
            member = memberService.registerMember(member);
        } else {
            member = optionalMember.get();
        }

        jwtTokenDto = tokenManager.createJwtTokenDto(member.getId(), member.getRole());
        member.updateRefreshToken(jwtTokenDto);

        return OauthLoginDto.Response.of(jwtTokenDto);
    }
}
