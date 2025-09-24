package com.example.trial_test.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private ObjectId id;
    private String userId;
    private String password;
    @NotBlank
    @Email
    private String email;
    private Set<Role> roles;

    private List<Salary> salaryDet;
    private List<Expense> expenseDet;
    private List<Investment> investmentDet;
}
