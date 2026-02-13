package com.suraj.learning_docker.services.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.suraj.learning_docker.dtos.auth.SignUpRequestDto;
import com.suraj.learning_docker.dtos.user.UserDto;
import com.suraj.learning_docker.entities.User;
import com.suraj.learning_docker.exceptions.UserAlreadyExistsException;
import com.suraj.learning_docker.mappers.UserMapper;
import com.suraj.learning_docker.repositories.UserRepository;
import com.suraj.learning_docker.security.CustomUserDetails;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUserById(Long id) {
        User user = this.getById(id);
        if (user == null)
            throw new UsernameNotFoundException("User not found with id: " + id);

        return UserMapper.toDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = this.getByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("User not found with email: " + email);

        return UserMapper.toDto(user);
    }

    @Override
    public UserDto getInfoFromAuthentication(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        return UserMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserDto createUser(SignUpRequestDto body) {
        User existingUser = this.getByEmail(body.getEmail());

        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with email " + body.getEmail() + " already exists.");
        }

        User newUser = UserMapper.toEntity(body);
        newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));

        User savedUser = this.userRepository.save(newUser);

        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserDto getAuthenticatedUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return this.getInfoFromAuthentication(auth);
    }

    @Override
    public User getById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

}
