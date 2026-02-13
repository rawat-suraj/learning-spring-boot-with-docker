package com.suraj.learning_docker.services.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.suraj.learning_docker.dtos.auth.SignUpRequestDto;
import com.suraj.learning_docker.dtos.user.UserDto;
import com.suraj.learning_docker.security.JwtUtil;
import com.suraj.learning_docker.services.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    public UserDto login(String email, String password) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return this.userService.getInfoFromAuthentication(authentication);
    }

    @Override
    public String generateToken(UserDto userDto) {
        return this.jwtUtil.generateToken(userDto);
    }

    @Override
    public UserDto signUp(SignUpRequestDto body) {
        return this.userService.createUser(body);
    }

}
