package com.example.trial_test.controller;
import com.example.trial_test.entity.Users;
import com.example.trial_test.service.UserService;
import com.example.trial_test.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Signup -> Default Role = USER
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Users user) {
        Users savedUser = userService.registerUser(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully!");
        response.put("userId", savedUser.getUserId());
        response.put("roles", savedUser.getRoles());
        return ResponseEntity.ok(response);
    }

    // ✅ Login -> Authenticate + Generate JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String userId = loginRequest.get("userId");
            String password = loginRequest.get("password");

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userId, password)
            );

            // If authentication is successful
            Users user = userService.getUserByUserId(userId);

            // Add role(s) to JWT claims
            Map<String, Object> claims = new HashMap<>();
            claims.put("roles", user.getRoles());

            String token = jwtUtil.generateTokenWithClaims(userId, claims);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", user.getUserId());
            response.put("roles", user.getRoles());

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials!");
        }
    }
}