package com.example.trial_test.controller;

import com.example.trial_test.entity.Users;
import com.example.trial_test.service.UserService;
import com.example.trial_test.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;
    // ✅ Validate token sent in Authorization header
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);

                if (jwtUtil.validateToken(token)) {
                    String username = jwtUtil.extractUsername(token);
                    Users userDet=userService.getUserByUserId(username);
                    response.put("valid", true);
//                    response.put("username", username);
                    response.put("User Details",userDet);
                    response.put("expiration", jwtUtil.extractExpiration(token));
                } else {
                    response.put("valid", false);
                    response.put("message", "Token is invalid or expired");
                }
            } else {
                response.put("valid", false);
                response.put("message", "Authorization header missing or malformed");
            }
        } catch (ExpiredJwtException e) {
            response.put("valid", false);
            response.put("message", "Token expired");
        } catch (JwtException e) {
            response.put("valid", false);
            response.put("message", "Invalid token");
        }

        return ResponseEntity.ok(response);
    }
}
