package com.example.teto.user.service;

import com.example.teto.security.Jwt.JwtProvider;
import com.example.teto.user.controller.dto.request.LoginRequest;
import com.example.teto.user.controller.dto.response.LoginResponse;
import com.example.teto.user.entity.User;
import com.example.teto.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(RuntimeException::new);
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException();
        }
        String accessToken = jwtProvider.createAccessToken(request.getEmail());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}