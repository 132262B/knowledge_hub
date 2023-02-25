package com.example.springjwt.api.member.controller;

import com.example.springjwt.api.member.dto.MemberInfoResponseDto;
import com.example.springjwt.api.member.service.MemberInfoService;
import com.example.springjwt.global.jwt.service.TokenManager;
import com.example.springjwt.global.resolver.memberinfo.MemberInfo;
import com.example.springjwt.global.resolver.memberinfo.MemberInfoDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberInfoController {

    private final MemberInfoService memberInfoService;

    @GetMapping("/info")
    public ResponseEntity<MemberInfoResponseDto> getMemberInfo(
            @MemberInfo MemberInfoDto memberInfoDto) {

        return ResponseEntity.ok(memberInfoService.getMemberInfo(memberInfoDto.getMemberId()));
    }
}
