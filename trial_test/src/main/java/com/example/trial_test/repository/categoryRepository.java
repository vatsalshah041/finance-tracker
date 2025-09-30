package com.example.trial_test.repository;

import com.example.trial_test.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.trial_test.entity.Categories;

import java.util.List;

public interface categoryRepository extends MongoRepository<Categories,String> {
    List<Categories> findByUserId(String userId);

}
