package com.suraj.learning_docker.services.user;

import org.springframework.security.core.Authentication;

import com.suraj.learning_docker.dtos.auth.SignUpRequestDto;
import com.suraj.learning_docker.dtos.user.UserDto;
import com.suraj.learning_docker.entities.User;

public interface UserService {
    UserDto getUserById(Long id);

    User getById(Long id);

    UserDto createUser(SignUpRequestDto body);

    UserDto getUserByEmail(String email);

    User getByEmail(String email);

    UserDto getInfoFromAuthentication(Authentication authentication);

    UserDto getAuthenticatedUserProfile();
}
