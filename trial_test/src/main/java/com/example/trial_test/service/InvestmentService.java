package com.example.trial_test.service;

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
import java.util.List;

@Component
public class InvestmentService {

    @Autowired
    private investmentRepository investmentRepository;

    public void postexp(Investment investment){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMyy");
        investment.setCode(today.format(formatter));
        System.out.println(investment);
        investmentRepository.save(investment);
    }

    public List<Investment> getById(String code){
        return investmentRepository.findByCode(code).orElse(Collections.emptyList());

    }


}
