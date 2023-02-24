package com.example.springjwt.api.login.controller;

import com.example.springjwt.api.login.dto.OauthLoginDto;
import com.example.springjwt.api.login.service.OauthLoginService;
import com.example.springjwt.api.login.validator.OauthValidator;
import com.example.springjwt.domain.member.constant.MemberType;
import com.example.springjwt.global.util.AuthorizationHeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OauthLoginController {

    private final OauthLoginService oauthLoginService;

    private final OauthValidator oauthValidator;

    @PostMapping("/login")
    public ResponseEntity<OauthLoginDto.Response> oauthLogin(@RequestBody OauthLoginDto.Request oauthLoginRequest,
                                                             HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorization);
        oauthValidator.validateMemberType(oauthLoginRequest.getMemberType());

        String accessToken = authorization.split(" ")[1];
        OauthLoginDto.Response response = oauthLoginService.oauthLogin(accessToken, MemberType.from(oauthLoginRequest.getMemberType()));

        return ResponseEntity.ok(response);
    }

}
