package com.example.client.controller;

import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import com.example.client.service.RestTemplateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientApiController {
    private final RestTemplateService restTemplateService;

    public ClientApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/get-user")
    public UserResponse getHello() {
        return restTemplateService.getUser();
    }

    @PostMapping("/post-user")
    public UserResponse postHello(@RequestBody UserRequest userRequest) {
        return restTemplateService.postUser(userRequest);
    }

    @PostMapping("/post-user-exchange")
    public UserResponse postHelloExchange(@RequestBody UserRequest userRequest) {
        return restTemplateService.exchange(userRequest);
    }

}
