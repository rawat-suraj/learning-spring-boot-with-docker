package com.suraj.learning_docker.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.learning_docker.dtos.user.UserDto;
import com.suraj.learning_docker.services.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * This endpoint gives data of any user by their ID.
     * @param id
     * @return
     */
    @GetMapping("/profile/{id}")
    public UserDto getUserProfile(@Valid @PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    /**
     * This endpoint gives access to the authenticated user's profile information.
     * @return
     */
    @GetMapping("/profile")
    public UserDto getUserProfile() {
        return this.userService.getAuthenticatedUserProfile();
    }

}
