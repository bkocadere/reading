package com.reading.reading.service.customer.impl;

import com.reading.reading.api.request.CustomerLoginRequest;
import com.reading.reading.service.customer.CustomerLoginService;
import com.reading.reading.service.customer.CustomerService;
import com.reading.reading.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerLoginServiceImpl implements CustomerLoginService {
    private final AuthenticationManager authenticationManager;

    private final CustomerService customerService;

    @Override
    public String login(CustomerLoginRequest loginRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (loginRequest.getPhone(), loginRequest.getPassword()));
        } catch (BadCredentialsException | NoSuchElementException ex) {
            throw new Exception("Incorrect username or password", ex);
        }

        UserDetails userDetails = getUserDetailsByPhone(loginRequest.getPhone());
        return JwtUtil.generateToken(userDetails);
    }

    private UserDetails getUserDetailsByPhone(String phone) {
        return new User(phone, customerService.getByPhone(phone).getPassword(), new ArrayList<>());
    }

}
