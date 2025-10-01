package com.example.trial_test.service;

import com.example.trial_test.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.trial_test.repository.expenseRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sound.midi.SysexMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExpenseService {

    @Autowired
    private expenseRepository expenseRepository;

    public boolean postexp(Expense expense,String userId){


        if(expense.getCode()==null){
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMyy");
            expense.setCode(today.format(formatter));
        }
        expense.setUserId(userId);
        try {
            expenseRepository.save(expense);
            return true;
        }
        catch(Exception e){
            return  false;
        }

    }

    public Map<String, Object> getAll(String userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);

        double total = expenses.stream()
                .mapToDouble(Expense::getValue) // assuming `value` is a number
                .sum();

        Map<String, Object> response = new HashMap<>();
        response.put("expenses", expenses);
        response.put("total", total);

        return response;
    }

    public List<Expense> getById(String code) {
        return expenseRepository.findByCode(code);  // already safe, no need for Optional
    }
    public double getSumByCode(String code) {
        List<Expense> expenses = getById(code);
        return expenses.stream()
                .mapToDouble(Expense::getValue)
                .sum();
    }


}
