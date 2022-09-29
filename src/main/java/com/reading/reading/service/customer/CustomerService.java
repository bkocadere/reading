package com.reading.reading.service.customer;

import com.reading.reading.api.request.AddCustomerRequest;
import com.reading.reading.model.customer.Customer;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface CustomerService {
    Customer add(@Valid AddCustomerRequest request);

    Customer getByPhone(@NotNull String phone);

    //List<CustomerDTO> filter();
}
