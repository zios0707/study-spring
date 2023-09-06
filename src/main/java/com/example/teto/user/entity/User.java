package com.example.teto.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Id;
import java.lang.annotation.Documented;

@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String email;

    private String name;

    @JsonIgnore
    private String password;

    @Builder
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}