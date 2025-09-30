package com.example.trial_test.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


@Data
public class Categories {

    @Id
    private ObjectId id;
    private String type;
    private String title;

    private String userID; //reference back to the user
}
