package com.example.trial_test.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private ObjectId id;
    private String user_id;
    private String password;
    @NotBlank
    @Email
    private String email;
    private String user_type;
}
