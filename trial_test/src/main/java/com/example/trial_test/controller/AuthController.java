package com.example.trial_test.controller;


import com.example.trial_test.config.JwtUtil;
import com.example.trial_test.entity.Role;
import com.example.trial_test.entity.Users;
import com.example.trial_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // -------------------- SIGNUP --------------------
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Users user) {

        // 1️⃣ Hash the password
        user.setPassword(user.getPassword());

        // 2️⃣ Default role if not provided
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Set.of(Role.ROLE_USER));
        }

        // 3️⃣ Save user to MongoDB
        Users savedUser = userService.saveUser(user);

        return ResponseEntity.ok(savedUser);
    }

    // -------------------- LOGIN --------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        System.out.println("Hellooo");
        try {
            // 1️⃣ Authenticate credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        // 2️⃣ Load user from DB
        Users dbUser = userService.findByUserId(user.getUserId());
        System.out.println(dbUser);
        // 3️⃣ Prepare roles for JWT
        Set<String> roles = dbUser.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.toSet());

        // 4️⃣ Generate JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        String token = JwtUtil.generateToken(dbUser.getUserId(), claims);

        // 5️⃣ Return token to client
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/test")
    public String test() {
        return "OK";
    }
}
