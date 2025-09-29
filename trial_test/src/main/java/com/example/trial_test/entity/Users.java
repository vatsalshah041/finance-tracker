package com.example.trial_test.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.bson.types.ObjectId;

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
    private List<Salary> salaryDet;
    private List<Expense> expenseDet;
    private List<Investment> investmentDet;
}
