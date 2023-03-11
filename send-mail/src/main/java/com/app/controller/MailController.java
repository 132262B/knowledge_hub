package com.app.controller;

import com.app.dto.SendMailRequest;
import com.app.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
public class MailController {

    private final MailService mailService;

    @PostMapping
    public ResponseEntity<Void> sendMail(@RequestBody SendMailRequest request) {
        mailService.sendMail(request);
        return ResponseEntity.noContent().build();
    }
}
