package com.example.trial_test.controller;

import com.example.trial_test.entity.Categories;
import com.example.trial_test.entity.Users;
import com.example.trial_test.service.CategoryService;
import com.example.trial_test.service.UserService;
import com.example.trial_test.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;
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
    @PostMapping("/addcat")
    public ResponseEntity<?> addCategories(@RequestBody Categories newCat,@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        String userId = jwtUtil.extractUsername(token);
        newCat.setUserId(userId);
        boolean a = categoryService.saveCat(newCat);
        return a
                ? ResponseEntity.ok("Category saved successfully")         // if true
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)  // if false
                .body("Failed to save category");
    }

    @GetMapping("/getcat")
    public ResponseEntity<List<Categories>> getCategories(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        String userId = jwtUtil.extractUsername(token);
        List<Categories> cats=categoryService.getCat(userId);
        return new ResponseEntity<>(cats, HttpStatus.OK);

    }

    @DeleteMapping("/delcat")
    public ResponseEntity<?> delCategories(@RequestBody Map<String, String> id,@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        String userId = jwtUtil.extractUsername(token);
        boolean b=categoryService.delCat(userId,id.get("id"));
        return b
                ? new ResponseEntity<>(true, HttpStatus.OK)
                : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateCat")
    public ResponseEntity<?> updateCat(@RequestBody Categories updateCat,@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        boolean b=categoryService.updateCat(updateCat);
        return b
                ? new ResponseEntity<>(true, HttpStatus.OK)
                : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

}


