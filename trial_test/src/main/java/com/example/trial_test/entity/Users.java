package com.example.trial_test.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Users {

    @EqualsAndHashCode.Include
    private ObjectId id;

    private String userId;
    private String password;

    @NotBlank
    @Email
    private String email;

    private List<String> roles;
    private List<Salary> salaryDet = new ArrayList<>();
    private List<Expense> expenseDet = new ArrayList<>();
    private List<Investment> investmentDet = new ArrayList<>();
    private List<Categories> categoryDet = new ArrayList<>();

}
