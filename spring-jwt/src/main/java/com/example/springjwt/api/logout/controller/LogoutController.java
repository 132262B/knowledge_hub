package com.example.springjwt.api.logout.controller;

import com.example.springjwt.api.logout.service.LogoutService;
import com.example.springjwt.global.util.AuthorizationHeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LogoutController {

    private final LogoutService logoutService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorization);

        String accessToken = authorization.split(" ")[1];
        logoutService.logout(accessToken);

        return ResponseEntity.ok("logout success");
    }
}
