package com.example.trial_test.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.trial_test.entity.Expense;

//mongorepositry has all the functions related to mongodb for crud operatioons

public interface expenseRepository extends MongoRepository <Expense,String> {
}
