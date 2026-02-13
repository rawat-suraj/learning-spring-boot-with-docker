package com.suraj.learning_docker.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.learning_docker.dtos.auth.AuthResponseDto;
import com.suraj.learning_docker.dtos.auth.SignInDtoRequest;
import com.suraj.learning_docker.dtos.auth.SignUpRequestDto;
import com.suraj.learning_docker.dtos.user.UserDto;
import com.suraj.learning_docker.services.auth.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseDto login(@Valid @RequestBody SignInDtoRequest signInDto) {
        UserDto userDto = this.authService.login(signInDto.getEmail(), signInDto.getPassword());
        String token = this.authService.generateToken(userDto);

        return new AuthResponseDto(token, userDto);
    }

    @PostMapping("/register")
    public AuthResponseDto  register(@Valid @RequestBody SignUpRequestDto body) {
        UserDto userDto = this.authService.signUp(body);
        String token = this.authService.generateToken(userDto);

        return new AuthResponseDto(token, userDto);
    }
}
