package com.example.trial_test.service;

import com.example.trial_test.entity.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.trial_test.repository.salaryRepository;

import java.util.Optional;

@Component
public class SalaryService {

    @Autowired
    private salaryRepository salaryRepository;

    public boolean addSalary(Salary sal){
        salaryRepository.save(sal);
        return true;
    }
    public Optional<Salary> getSal(String code)
    {
        return salaryRepository.findByCode(code);

    }

}
