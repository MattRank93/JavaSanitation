package com.example.javasanitation.responseobjects;

import lombok.Value;

@Value
public class UserResponse {
    String username;
    String email;
    String firstname;
    String lastname;
    String phone;
}
