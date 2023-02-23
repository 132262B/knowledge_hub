package com.example.springjwt.external.oauth.service;

import com.example.springjwt.external.oauth.model.OAuthAttributes;

public interface SocialLoginApiService {

    OAuthAttributes getUserInfo(String accessToken);
}
