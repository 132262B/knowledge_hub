package com.example.springjwt.api.member.dto;

import com.example.springjwt.domain.member.constant.Role;
import com.example.springjwt.domain.member.entitiy.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoResponseDto {

    private Long memberId;
    private String email;
    private String memberName;
    private String profile;
    private Role role;

    public static MemberInfoResponseDto of(Member member) {
        return MemberInfoResponseDto.builder()
                .memberId(member.getId())
                .memberName(member.getMemberName())
                .email(member.getEmail())
                .profile(member.getProfile())
                .role(member.getRole())
                .build();
    }
}
