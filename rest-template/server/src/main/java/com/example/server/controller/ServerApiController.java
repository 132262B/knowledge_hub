package com.example.server.controller;


import com.example.server.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/get-user")
    public User getUser(User user) {
        return user;
    }

    @PostMapping("/post-user")
    public User postUser(@RequestBody User user,
                         @RequestHeader("x-authorization") String authorization,
                         @RequestHeader("header-data") String headerData) {

        log.info(" 헤더 정보 1. {} , {}", authorization, headerData);
        log.info(user.toString());
        return user;
    }
}
