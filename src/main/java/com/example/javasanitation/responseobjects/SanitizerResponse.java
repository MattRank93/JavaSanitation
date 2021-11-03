package com.example.javasanitation.responseobjects;

import lombok.Value;

@Value
public class SanitizerResponse {
    String sanitizedString;
    boolean badPass;

}

