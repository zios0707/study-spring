package com.example.teto.user.service;

import com.example.teto.user.controller.dto.request.SignRequest;
import com.example.teto.user.entity.User;
import com.example.teto.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SignService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void Sign(SignRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }
        if(userRepository.findByName(request.getName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이름 입니다.");
        }
        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        userRepository.save(user);
    }
}
