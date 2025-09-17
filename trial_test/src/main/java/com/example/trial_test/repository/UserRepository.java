package com.example.trial_test.repository;

import com.example.trial_test.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<Users, ObjectId> {

    Optional<Users> findByUserId(String userId);
}
