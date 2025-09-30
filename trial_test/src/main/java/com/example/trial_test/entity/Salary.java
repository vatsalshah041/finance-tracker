package com.example.trial_test.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class Salary {
    @Id
    private ObjectId id;
    private String code;
    private int value;

    private String userId; // reference to user
}
