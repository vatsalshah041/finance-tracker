package com.example.trial_test.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class Investment {
    @Id
    private ObjectId id;
    private String code;
    private String title;
    private int value;
    private String inv_type;

    private ObjectId userId; //reference to user
}
