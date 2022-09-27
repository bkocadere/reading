package com.reading.reading.service.customer;

import com.reading.reading.api.request.AddCustomerRequest;
import com.reading.reading.model.customer.Customer;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface CustomerService {
    Customer add(@Valid AddCustomerRequest request);

    //List<CustomerDTO> filter();
}
