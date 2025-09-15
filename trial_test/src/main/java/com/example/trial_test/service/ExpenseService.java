package com.example.trial_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.trial_test.repository.expenseRepository;
import org.springframework.stereotype.Component;

@Component
public class ExpenseService {

    @Autowired
    private expenseRepository expenseRepository;


}
