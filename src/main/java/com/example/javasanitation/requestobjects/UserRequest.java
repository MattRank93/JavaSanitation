package com.example.javasanitation.requestobjects;


import lombok.Value;

@Value
public class UserRequest {
    String username;
    String password;
    String email;
    String firstname;
    String lastname;
    String phone;

}
