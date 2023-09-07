package com.example.teto.user.controller;

import com.example.teto.user.controller.dto.request.LoginRequest;
import com.example.teto.user.controller.dto.request.SignRequest;
import com.example.teto.user.controller.dto.response.LoginResponse;
import com.example.teto.user.entity.User;
import com.example.teto.user.service.LoginService;
import com.example.teto.user.service.UserFacade;
import com.example.teto.user.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/what")
@RequiredArgsConstructor
public class RestController {
    private final LoginService loginService;
    private final SignService signService;
    private final UserFacade userFacade;

    @PostMapping("/sign")
    public void sign(@RequestBody SignRequest request) {
        // 회원가입
        signService.Sign(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        // 로그인
        return loginService.login(request);
    }

    @GetMapping("/user")
    public User Info() {
        // 프로필 호출
        return userFacade.getInfo();
    }
}