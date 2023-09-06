package com.example.teto.user.service;

import com.example.teto.user.controller.dto.request.SignRequest;
import com.example.teto.user.entity.User;
import com.example.teto.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void Sign(SignRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent() ||
        userRepository.findByName(request.getName()).isPresent()) {
            throw new RuntimeException();
        }

        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
    }
}
