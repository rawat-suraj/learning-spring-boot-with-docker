package com.suraj.learning_docker.services.auth;

import com.suraj.learning_docker.dtos.auth.SignUpRequestDto;
import com.suraj.learning_docker.dtos.user.UserDto;

public interface AuthService {
    UserDto login(String email, String password);

    String generateToken(UserDto userDto);

    UserDto signUp(SignUpRequestDto body);
}
