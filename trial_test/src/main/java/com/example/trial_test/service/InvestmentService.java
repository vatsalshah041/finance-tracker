package com.example.trial_test.service;

import com.example.trial_test.entity.Expense;
import com.example.trial_test.entity.Investment;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.trial_test.repository.investmentRepository;
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
public class InvestmentService {

    @Autowired
    private investmentRepository investmentRepository;

    public boolean postinv(Investment invest,String userId){


        if(invest.getCode()==null){
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMyy");
            invest.setCode(today.format(formatter));
        }
        invest.setUserId(userId);
        try {
            investmentRepository.save(invest);
            return true;
        }
        catch(Exception e){
            return  false;
        }

    }

    public Map<String, Object> getAll(String userId) {
        List<Investment> investments = investmentRepository.findByUserId(userId);

        double total = investments.stream()
                .mapToDouble(Investment::getValue) // assuming `value` is a number
                .sum();

        Map<String, Object> response = new HashMap<>();
        response.put("expenses", investments);
        response.put("total", total);

        return response;
    }

    public Map<String, Object> getById(String code,String userId) {
        List<Investment> investments= investmentRepository.findByCode(code);  // already safe, no need for Optional
        double total = investments.stream()
                .mapToDouble(Investment::getValue) // assuming `value` is a number
                .sum();

        Map<String, Object> response = new HashMap<>();
        response.put("expenses", investments);
        response.put("total", total);

        return response;
    }

}
