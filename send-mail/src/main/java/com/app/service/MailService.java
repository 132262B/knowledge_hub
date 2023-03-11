package com.app.service;

import com.app.dto.SendMailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public void sendMail(SendMailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getAddress());
        message.setSubject(request.getMessage());
        message.setText(request.getMessage());
        javaMailSender.send(message);
    }
}
