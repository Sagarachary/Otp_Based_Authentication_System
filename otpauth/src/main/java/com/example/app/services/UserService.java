package com.example.app.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.app.entities.Tokens;
import com.example.app.entities.Users;
import com.example.app.repositories.TokenRepository;
import com.example.app.repositories.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private JavaMailSender mailSender;

    public Users findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void sendOtp(Users users) {
        String otp = String.format("%06d", new Random().nextInt(999999));

        Tokens token = new Tokens();
        token.setUsers(users);
        token.setOtp(otp);
        token.setCreatedAt(LocalDateTime.now());

        tokenRepository.save(token);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(users.getEmail());
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);

        mailSender.send(message);
    }

    public boolean verifyOtp(String otp) {
        Tokens token = tokenRepository.findByOtp(otp);

        if (token == null) return false;

        long minutes = ChronoUnit.MINUTES.between(
                token.getCreatedAt(),
                LocalDateTime.now()
        );

        if (minutes > 1) {
            tokenRepository.delete(token);
            return false;
        }

        tokenRepository.delete(token);
        return true;
    }
}

