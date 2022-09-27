package com.reading.reading.api.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AddCustomerRequest {
    @NotNull
    @Size(min = 8)
    private String password;

    @NotNull
    @Size(min = 10, max = 13)
    private String phone;

    @NotNull
    @Size(max = 100)
    private String fullName;
}
