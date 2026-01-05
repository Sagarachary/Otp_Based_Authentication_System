package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entities.Tokens;

public interface TokenRepository extends JpaRepository<Tokens, Integer> {
    Tokens findByOtp(String otp);
}
