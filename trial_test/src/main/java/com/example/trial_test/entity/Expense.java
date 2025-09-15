package com.example.trial_test.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "financeDB")
public class Expense {

//    @Id
    private int id;
    private int title;
    private int value;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
