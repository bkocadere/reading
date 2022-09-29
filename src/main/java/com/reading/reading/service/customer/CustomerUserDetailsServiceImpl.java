package com.reading.reading.service.customer;

import com.reading.reading.model.customer.Customer;
import com.reading.reading.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        return getUserDetailsByPhone(phone);
    }

    private UserDetails getUserDetailsByPhone(String phone) {
        return new User(phone, getByPhone(phone).getPassword(), new ArrayList<>());
    }

    private Customer getByPhone(String phone) {
        return customerRepository.findOneByPhone(phone).orElseThrow();
    }
}
