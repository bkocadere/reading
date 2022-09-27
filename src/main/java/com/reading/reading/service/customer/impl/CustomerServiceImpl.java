package com.reading.reading.service.customer.impl;

import com.reading.reading.api.request.AddCustomerRequest;
import com.reading.reading.enums.Status;
import com.reading.reading.model.customer.Customer;
import com.reading.reading.repository.customer.CustomerRepository;
import com.reading.reading.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer add(AddCustomerRequest request) {
        Customer customer = prepareCustomer(request);
        return customerRepository.save(customer);
    }

    private Customer prepareCustomer(AddCustomerRequest request) {
        Customer customer = new Customer();
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setPhone(checkPhone(request.getPhone()));
        customer.setFullName(request.getFullName());
        customer.setCreatedDate(OffsetDateTime.now());
        customer.setStatus(Status.ACTIVE);
        return customer;
    }

    private String checkPhone(String phone) {
        if (customerRepository.existsByPhone(phone)) {
            throw new NotFoundException("Phone number already exists!");
        }
        return phone;
    }
}
