package com.suraj.learning_docker.dtos.auth;

import com.suraj.learning_docker.dtos.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDto {
    private String token;
    private UserDto user;
}
