package com.example.javasanitation.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "SpringData")
public class RunData {

    @MongoId
    @Persistent
    private String id;

    private String username;

    private Integer distance;

    private Integer totalTime;




    public RunData(String username, Integer distance,  Integer totalTime, String phone, String role) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.distance = distance;
        this.totalTime = totalTime;

    }


}
