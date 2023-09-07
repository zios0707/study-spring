package com.example.teto.user.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class SignRequest {

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String email;

}
