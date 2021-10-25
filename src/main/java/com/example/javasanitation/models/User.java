package com.example.javasanitation.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;


@Document(collection = "SpringUser")
public class User {

    @MongoId
    @Persistent
    private final UUID uuid = UUID.randomUUID();

    @NotBlank
    @Size(max = 100)
    @Indexed(unique=true)
    private String username;

    @NotBlank
    @Size(max = 100)
    @Email
    @Indexed(unique=true)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(max = 20)
    private String firstname;

    @NotBlank
    @Size(max = 20)
    private String lastname;

    @NotBlank
    private String role;

    private String phone;


    public User(String email, String username, String password, String firstname, String lastname, String phone, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.role = role;
    }

}
