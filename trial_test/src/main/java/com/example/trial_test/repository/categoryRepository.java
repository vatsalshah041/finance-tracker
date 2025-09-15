package com.example.trial_test.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.trial_test.entity.Categories;

public interface categoryRepository extends MongoRepository<Categories,String> {
}
