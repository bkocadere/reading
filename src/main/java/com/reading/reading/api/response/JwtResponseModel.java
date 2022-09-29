package com.reading.reading.api.response;

import lombok.Getter;

@Getter
public class JwtResponseModel {
    private final String token;

    public JwtResponseModel(String token) {
        this.token = token;
    }
}