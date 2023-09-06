package com.example.teto.user.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignRequest {
    private String name;
    private String password;
    private String email;
}
