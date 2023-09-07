package com.example.teto.user.controller;

import com.example.teto.user.controller.dto.request.LoginRequest;
import com.example.teto.user.controller.dto.request.SignRequest;
import com.example.teto.user.controller.dto.response.LoginResponse;
import com.example.teto.user.service.LoginService;
import com.example.teto.user.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/what")
@RequiredArgsConstructor
public class RestController {
    private final LoginService loginService;
    private final SignService signService;

    @PostMapping("/sign")
    public void sign(@RequestBody SignRequest request) {
        signService.Sign(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return loginService.login(request);
    }
}