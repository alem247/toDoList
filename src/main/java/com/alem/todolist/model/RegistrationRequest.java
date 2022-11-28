package com.alem.todolist.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private UserRole role;
}
