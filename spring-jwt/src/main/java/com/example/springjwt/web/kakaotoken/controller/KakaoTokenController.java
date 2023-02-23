package com.example.springjwt.web.kakaotoken.controller;

import com.example.springjwt.web.kakaotoken.client.KakaoTokenClient;
import com.example.springjwt.web.kakaotoken.dto.KakaoTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class KakaoTokenController {

    private final KakaoTokenClient kakaoTokenClient;

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.client.secret}")
    private String clientSecret;

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

//    @GetMapping("/oauth/kakao/callback")
//    public @ResponseBody KakaoTokenDto.Response loginCallBack(String code) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        String contentType = "application/x-www-form-urlencoded;charset=utf-8";
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code");
//        params.add("client_id", clientId);
//        params.add("client_secret",clientSecret);
//        params.add("redirect_uri", "http://localhost:8080/oauth/kakao/callback");
//        params.add("code", code);
//
//        URI uri = UriComponentsBuilder
//                .fromUriString("https://kauth.kakao.com")
//                .path("/oauth/token")
//                .build()
//                .toUri();
//
//        RequestEntity<MultiValueMap<String,String>> entity = RequestEntity.post(uri)
//                .header("Content-type", contentType)
//                .body(params);
//
//        ResponseEntity<String> kakaoToken = restTemplate.exchange(entity, String.class);
//
//        KakaoTokenDto.Response response = null;
//        try {
//            response =  objectMapper.readValue(kakaoToken.getBody(), KakaoTokenDto.Response.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        return response;
//    }

    @GetMapping("/oauth/kakao/callback")
    public @ResponseBody String loginCallback(String code) {
        String contentType = "application/x-www-form-urlencoded;charset=utf-8";
        KakaoTokenDto.Request kakaoTokenRequestDto = KakaoTokenDto.Request.builder()
                .client_id(clientId)
                .client_secret(clientSecret)
                .grant_type("authorization_code")
                .code(code)
                .redirect_uri("http://localhost:8080/oauth/kakao/callback")
                .build();
        KakaoTokenDto.Response kakaoToken = kakaoTokenClient.requestKakaoToken(contentType, kakaoTokenRequestDto);
        return "kakao toekn : " + kakaoToken;
    }
}
