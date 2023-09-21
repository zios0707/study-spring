package com.example.teto.user.service;

import com.example.teto.security.Jwt.JwtProvider;
import com.example.teto.user.controller.dto.request.LoginRequest;
import com.example.teto.user.controller.dto.response.LoginResponse;
import com.example.teto.user.entity.User;
import com.example.teto.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 EMAIL 입니다."));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.info(String.valueOf(user));
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        String accessToken = jwtProvider.createAccessToken(request.getEmail());
        String refreshToken = jwtProvider.createRefreshToken(request.getEmail());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}