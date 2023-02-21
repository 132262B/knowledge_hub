package com.example.springjwt.web.kakaotoken.controller;

import com.example.springjwt.web.kakaotoken.dto.KakaoTokenDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@RequiredArgsConstructor
public class KakaoTokenController {

    private final ObjectMapper objectMapper;

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.client.secret}")
    private String clientSecret;

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/oauth/kakao/callback")
    public @ResponseBody KakaoTokenDto.Response loginCallBack(String code) {
        RestTemplate restTemplate = new RestTemplate();

        String contentType = "application/x-www-form-urlencoded;charset=utf-8";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret",clientSecret);
        params.add("redirect_uri", "http://localhost:8080/oauth/kakao/callback");
        params.add("code", code);

        URI uri = UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com")
                .path("/oauth/token")
                .build()
                .toUri();

        RequestEntity<MultiValueMap<String,String>> entity = RequestEntity.post(uri)
                .header("Content-type", contentType)
                .body(params);

        ResponseEntity<String> kakaoToken = restTemplate.exchange(entity, String.class);

        KakaoTokenDto.Response response = null;
        try {
            response =  objectMapper.readValue(kakaoToken.getBody(), KakaoTokenDto.Response.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return response;
    }
}
