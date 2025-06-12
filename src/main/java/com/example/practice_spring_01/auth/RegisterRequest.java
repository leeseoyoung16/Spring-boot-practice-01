package com.example.practice_spring_01.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest
{
    private String username;

    private String password;

    private String email;

}
