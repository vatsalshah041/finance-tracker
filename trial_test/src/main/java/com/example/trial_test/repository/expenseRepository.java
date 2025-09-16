package com.example.trial_test.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.trial_test.entity.Expense;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

//mongorepositry has all the functions related to mongodb for crud operatioons

public interface expenseRepository extends MongoRepository <Expense, ObjectId> {
    Optional<List<Expense>> findByCode(String Code);
}
