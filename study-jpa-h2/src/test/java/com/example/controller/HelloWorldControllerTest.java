package com.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloWorldTest() throws Exception {

        // perform = 수행할 객체 생성
        // MockMvcRequestBuilders.get 어디로 어떤 타입으로 요청할지
        mockMvc.perform(MockMvcRequestBuilders.get("/hello-world"))
                // 결과출력
                .andDo(print())
                // andExpect(결과는) : status는 200인지
                .andExpect(status().isOk())
                // andExpect(결과는) : 값은 String 형태의 helloWorld 인지
                .andExpect(content().string("hello-world"));

    }

}