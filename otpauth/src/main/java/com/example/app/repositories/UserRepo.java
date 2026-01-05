package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entities.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
