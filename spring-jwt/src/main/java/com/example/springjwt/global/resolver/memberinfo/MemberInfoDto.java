package com.example.springjwt.global.resolver.memberinfo;

import com.example.springjwt.domain.member.constant.Role;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MemberInfoDto {

    private Long memberId;
    private Role role;
}
