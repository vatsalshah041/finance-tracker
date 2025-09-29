package com.example.trial_test.service;

import com.example.trial_test.entity.Users;
import com.example.trial_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ Register new user with default USER role
    public Users registerUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList("USER")); // default role
        return userRepository.save(user);
    }

    // ✅ Fetch user by userId
    public Users getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}