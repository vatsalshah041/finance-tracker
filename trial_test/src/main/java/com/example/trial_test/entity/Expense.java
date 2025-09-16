package com.example.trial_test.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class Expense {
    @Id
    private ObjectId id;
    private String code;//non unique
    private String   title;
    private int value;

}
