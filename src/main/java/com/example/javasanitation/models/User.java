package com.example.javasanitation.models;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
    private UUID id;

    @NotBlank( message = "must contain username")
    @Size(max = 100)
    @Indexed(unique=true)
    private String username;

    @NotBlank( message = "must contain email")
    @Size(max = 100)
    @Email
    @Indexed(unique=true)
    private String email;

    @NotBlank( message = "must contain password")
    @Size(max = 120)
    private String password;

    @NotBlank( message = "must contain first name")
    @Size(max = 20)
    private String firstname;

    @NotBlank( message = "must contain last name")
    @Size(max = 20)
    private String lastname;

    @NotBlank
    private String role = "user";

    private String phone;


    public User(String email, String username, String password, String firstname, String lastname, String phone, String role) { ;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
//        this.role = role;
    }


}
