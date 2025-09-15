package com.example.trial_test.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Data
public class Categories {

    @Id
    private String id;
    private String type;
    private String title;

}
