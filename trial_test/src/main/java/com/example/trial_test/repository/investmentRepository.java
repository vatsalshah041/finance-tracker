package com.example.trial_test.repository;

import com.example.trial_test.entity.Investment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface investmentRepository extends MongoRepository<Investment, ObjectId> {
}
