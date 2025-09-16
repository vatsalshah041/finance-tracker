package com.example.trial_test.controller;


import com.example.trial_test.entity.Expense;
import com.example.trial_test.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expense")

public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<?> postExpense(@RequestBody Expense expense){
        System.out.println(expense);
        expenseService.postexp(expense);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @GetMapping("id/{code}")
    public ResponseEntity<List<Expense>> getExp(@PathVariable String code) {
        List<Expense> expenses = expenseService.getById(code);

        if (expenses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        }
    }
}
