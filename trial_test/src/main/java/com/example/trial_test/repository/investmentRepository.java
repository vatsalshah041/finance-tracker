package com.example.trial_test.repository;

import com.example.trial_test.entity.Expense;
import com.example.trial_test.entity.Investment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface investmentRepository extends MongoRepository<Investment, ObjectId> {
    Optional<List<Investment>> findByCode(String Code);

}
