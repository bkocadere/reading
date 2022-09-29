package com.reading.reading.controller;

import com.reading.reading.api.request.AddCustomerRequest;
import com.reading.reading.api.request.CustomerLoginRequest;
import com.reading.reading.api.response.JwtResponseModel;
import com.reading.reading.api.response.customer.CustomerDTO;
import com.reading.reading.model.order.OrderBook;
import com.reading.reading.service.customer.CustomerLoginService;
import com.reading.reading.service.customer.CustomerService;
import com.reading.reading.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    private final CustomerLoginService customerLoginService;

    private final OrderService orderService;

    @PostMapping("/login")
    public JwtResponseModel login(@RequestBody CustomerLoginRequest loginRequest) throws Exception {
        return new JwtResponseModel(customerLoginService.login(loginRequest));
    }

    @GetMapping("/{id}/orders")
    public Page<OrderBook> getOrders(@PathVariable Long id, @RequestParam(required = false) Integer size,
                                     @RequestParam(required = false) Integer page) {
        return orderService.getOrderByCustomerId(id, size, page);
    }

    @PostMapping
    public CustomerDTO add(@RequestBody AddCustomerRequest request) {
        return customerService.add(request).toDTO();
    }
}
