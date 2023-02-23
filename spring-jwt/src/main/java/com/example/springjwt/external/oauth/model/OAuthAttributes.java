package com.example.springjwt.external.oauth.model;


import com.example.springjwt.domain.member.constant.MemberType;
import com.example.springjwt.domain.member.constant.Role;
import com.example.springjwt.domain.member.entitiy.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class OAuthAttributes {

    private String name;
    private String email;
    private String profile;

    private MemberType memberType;

    public Member toMemberEntity(MemberType memberType, Role role) {
        return Member.builder()
                .memberName(name)
                .email(email)
                .memberType(memberType)
                .profile(profile)
                .role(role)
                .build();
    }
}
