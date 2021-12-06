package com.example.javasanitation.requestobjects;
import com.google.protobuf.Int32Value;
import lombok.Value;

import java.util.UUID;

@Value
public class AppUploadRequest {

    String deviceId;
    String fileId;
    int fileSize;
    String fileType;
    String firmwareVersion;
    String id;

}
