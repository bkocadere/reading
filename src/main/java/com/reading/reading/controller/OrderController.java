package com.reading.reading.controller;

import com.reading.reading.api.request.AddOrderBookRequest;
import com.reading.reading.model.order.OrderBook;
import com.reading.reading.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public List<OrderBook> add(@RequestBody AddOrderBookRequest request) {
        return orderService.add(request);
    }
}
