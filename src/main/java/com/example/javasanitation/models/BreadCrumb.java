package com.example.javasanitation.models;


import com.google.protobuf.Int32Value;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "SpringData")
public class BreadCrumb {

    @MongoId
    @Persistent
    public Int32Value lat;
    public Int32Value longi;
    public Int32Value height;
    public Int32Value gSpeed;






}
