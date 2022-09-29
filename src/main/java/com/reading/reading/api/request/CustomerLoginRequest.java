package com.reading.reading.api.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerLoginRequest {
    @NotNull
    private String phone;

    @NotNull
    private String password;
}
