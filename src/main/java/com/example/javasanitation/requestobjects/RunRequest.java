package com.example.javasanitation.requestobjects;


import lombok.Value;

import java.util.UUID;

@Value
public class RunRequest {
    String id;
    String username;
    Integer distance;
    Integer totaltime;
}
