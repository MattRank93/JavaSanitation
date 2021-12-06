package com.example.javasanitation.models;


import com.google.protobuf.Int32Value;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "SpringData")
public class AppUpload {

//    public AppUpload(String deviceId, String fileId, int fileSize, String fileType, String firmwareVersion, UUID id) {
//        this.deviceId = deviceId;
//        this.fileId = fileId;
//        this.fileSize = fileSize;
//        this.fileType = fileType;
//        this.firmwareVersion = firmwareVersion;
//        this.id = id;
//    }

    @MongoId
    @Persistent
    public String deviceId;
    public String fileId;
    public int fileSize;
    public String fileType;
    public String firmwareVersion;
    public String id;




}
