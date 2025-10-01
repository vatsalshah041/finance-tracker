package com.example.trial_test.controller;


import com.example.trial_test.entity.Expense;
import com.example.trial_test.service.ExpenseService;
import com.example.trial_test.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")

public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private JwtUtil jwtUtil;

//    @PostMapping
//    public ResponseEntity<?> postExpense(@RequestBody Expense expense) {
//        System.out.println(expense);
//        expenseService.postexp(expense);
//        return new ResponseEntity<>(true, HttpStatus.OK);
//    }
//
//    @GetMapping("id/{code}")
//    public ResponseEntity<?> getExp(@PathVariable String code) {
//        List<Expense> expenses = expenseService.getById(code);
////        System.out.println(sums);
//        if (expenses.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            double total = expenseService.getSumByCode(code);
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("expenses", expenses);
//            response.put("total", total);
//
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
//    }
    @PostMapping("/addExp")
    public ResponseEntity<?> addExpense(@RequestBody Expense newExpense,@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        String userId = jwtUtil.extractUsername(token);
        boolean a=expenseService.postexp(newExpense,userId);
        return a? new ResponseEntity<>(true,HttpStatus.OK):new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @GetMapping("/getAllExp")
    public ResponseEntity<?> getAllExp(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        String userId = jwtUtil.extractUsername(token);
        Map<String, Object> expenseList =expenseService.getAll(userId);
        return new ResponseEntity<>(expenseList,HttpStatus.OK);
    }
    @GetMapping("/getExpByCode")
    public ResponseEntity<?> getByCode(@RequestBody Map<String, String> code,@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        String userId = jwtUtil.extractUsername(token);
        Map<String, Object> expenseList =expenseService.getById(code.get("code"),userId);
        return new ResponseEntity<>(expenseList,HttpStatus.OK);
    }
}
