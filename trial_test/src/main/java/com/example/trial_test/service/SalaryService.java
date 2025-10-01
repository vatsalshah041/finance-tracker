package com.example.trial_test.service;

import com.example.trial_test.entity.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.trial_test.repository.salaryRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
public class SalaryService {

    @Autowired
    private salaryRepository salaryRepository;

    public boolean addSalary(String userid,Salary sal){
        if(sal.getCode()==null){
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMyy");
            sal.setCode(today.format(formatter));
        }
        sal.setUserId(userid);
        try {
            salaryRepository.save(sal);
            return true;
        }
        catch(Exception e){
            return  false;
        }
//        return true;
    }
    public Optional<Salary> getSal(String code)
    {
        return salaryRepository.findByCode(code);

    }
    public List<Salary> getAllSal(String userid){
        return salaryRepository.findByUserId(userid);
    }

}
