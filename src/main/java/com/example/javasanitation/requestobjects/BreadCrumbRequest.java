package com.example.javasanitation.requestobjects;


import com.google.protobuf.Int32Value;
import lombok.Value;

import java.util.UUID;

@Value
public class BreadCrumbRequest {

    Int32Value lat;
    Int32Value longi;
    Int32Value height;
    Int32Value gSpeed;
}
