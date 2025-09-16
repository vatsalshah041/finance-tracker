package com.example.trial_test.repository;

import com.example.trial_test.entity.Salary;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface salaryRepository extends MongoRepository<Salary, ObjectId> {
    Optional<Salary> findByCode(String code);
}
