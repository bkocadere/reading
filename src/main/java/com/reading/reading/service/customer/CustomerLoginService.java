package com.reading.reading.service.customer;

import com.reading.reading.api.request.CustomerLoginRequest;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface CustomerLoginService {
    String login(@Valid CustomerLoginRequest loginRequest) throws Exception;
}
