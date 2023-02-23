package com.example.springjwt.api.login.validator;


import com.example.springjwt.domain.member.constant.MemberType;
import org.springframework.stereotype.Service;

@Service
public class OauthValidator {

    public void validateMemberType(String memberType) {
        if(!MemberType.isMemberType(memberType)) {
            throw new RuntimeException("에러");
        }
    }

}
