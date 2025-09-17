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
import java.util.List;

@Component
public class ExpenseService {

    @Autowired
    private expenseRepository expenseRepository;

    public void postexp(Expense expense){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMyy");
        expense.setCode(today.format(formatter));
        System.out.println(expense);
        expenseRepository.save(expense);
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
