package com.suraj.learning_docker.mappers;

import com.suraj.learning_docker.dtos.auth.SignUpRequestDto;
import com.suraj.learning_docker.dtos.user.UserDto;
import com.suraj.learning_docker.entities.User;

public class UserMapper {
    public static UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public static User toEntity(SignUpRequestDto userDto) {
        User entity = new User();
        entity.setName(userDto.getName());
        entity.setEmail(userDto.getEmail());
        entity.setPassword(userDto.getPassword());
        return entity;
    }
}
