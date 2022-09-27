package com.reading.reading.controller;

import com.reading.reading.api.request.AddCustomerRequest;
import com.reading.reading.api.response.customer.CustomerDTO;
import com.reading.reading.model.order.OrderBook;
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

    private final OrderService orderService;


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
