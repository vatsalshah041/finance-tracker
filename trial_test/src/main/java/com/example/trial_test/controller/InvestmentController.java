package com.example.trial_test.controller;


import com.example.trial_test.entity.Investment;
import com.example.trial_test.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/investment")

public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @PostMapping
    public ResponseEntity<?> postInvestment(@RequestBody Investment investment){
        System.out.println(investment);
        investmentService.postexp(investment);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @GetMapping("id/{code}")
    public ResponseEntity<List<Investment>> getExp(@PathVariable String code) {
        List<Investment> investments = investmentService.getById(code);

        if (investments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(investments, HttpStatus.OK);
        }
    }
}
