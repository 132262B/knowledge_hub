package com.example.springjwt.api.token.controller;

import com.example.springjwt.api.token.dto.AccessTokenResponseDto;
import com.example.springjwt.api.token.service.TokenService;
import com.example.springjwt.global.util.AuthorizationHeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/access-token/issue")
    public ResponseEntity<AccessTokenResponseDto> createAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorization);

        String refreshToken = authorization.split(" ")[1];
        AccessTokenResponseDto accessTokenResponseDto = tokenService.createAccessTokenByRefreshToken(refreshToken);

        return ResponseEntity.ok(accessTokenResponseDto);
    }
}
