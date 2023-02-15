package com.example.client.service;

import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    private final String SERVER_URL = "http://localhost:9090";

    public UserResponse getUser() {
        URI uri = UriComponentsBuilder
                .fromUriString(SERVER_URL)  // 도메인
                .path("/api/server/user")       // Path
                .queryParam("name", "igor")
                .queryParam("age", 20)
                .encode()                        // 인코드
                .build()                         // 생성
                .toUri();                        // URI Class 로 변경.

        System.out.println(uri);

        RestTemplate restTemplate = new RestTemplate();

        // Object 로 받으면 결과만 타입을 정해서 받을 수 있음.
        // String result = restTemplate.getForObject(uri , String.class);

        // Entity 로 받으면 결과와 response 정보까지 받을 수 있음.
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getStatusCodeValue());
        System.out.println(result.getHeaders());
        System.out.println(result.getBody());

        return result.getBody();
    }

    public UserResponse postUser(UserRequest userRequest) {

        URI uri = UriComponentsBuilder
                .fromUriString(SERVER_URL)
                .path("api/server/post-user")
                .encode()
                .build()
                .toUri();


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> result = restTemplate.postForEntity(uri, userRequest, UserResponse.class);

        System.out.println(result.getHeaders());

        return result.getBody();
    }


    public UserResponse exchange(UserRequest userRequest) {

        URI uri = UriComponentsBuilder
                .fromUriString(SERVER_URL)
                .path("api/server/post-user")
                .encode()
                .build()
                .toUri();

        RequestEntity<UserRequest> requestEntity = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcdefg")
                .header("header-data", "1234567890")
                .body(userRequest);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> result = restTemplate.exchange(requestEntity, UserResponse.class);

        System.out.println(result.getHeaders());
        System.out.println(result.getBody());

        return result.getBody();
    }
}

