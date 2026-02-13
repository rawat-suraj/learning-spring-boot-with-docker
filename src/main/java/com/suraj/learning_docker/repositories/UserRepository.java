package com.suraj.learning_docker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suraj.learning_docker.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
