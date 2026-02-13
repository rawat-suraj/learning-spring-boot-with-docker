package com.suraj.learning_docker.dtos.user;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
}
