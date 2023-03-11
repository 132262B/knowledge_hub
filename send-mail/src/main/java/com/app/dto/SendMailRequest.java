package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendMailRequest {

    private String address;

    private String title;

    private String message;
}
