package com.example.trial_test.controller;


import com.example.trial_test.entity.Salary;
import com.example.trial_test.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.trial_test.repository.salaryRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;
    @Autowired
    private salaryRepository salaryRepository;

    @PostMapping
    public ResponseEntity<?> addSalary(@RequestBody Salary sal){
        boolean ack=salaryService.addSalary(sal);
        return ack? new ResponseEntity<>(true, HttpStatus.OK):new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);

    }
    @GetMapping
    public ResponseEntity<List<Salary>> getAll(){
        return new ResponseEntity<>(salaryRepository.findAll(),HttpStatus.OK);

    }
    @GetMapping("/id/{code}")
    public ResponseEntity<Salary> getById(@PathVariable String code){
        return salaryService.getSal(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
