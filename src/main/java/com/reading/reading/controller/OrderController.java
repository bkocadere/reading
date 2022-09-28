package com.reading.reading.controller;

import com.reading.reading.api.request.AddOrderBookRequest;
import com.reading.reading.api.response.order.OrderBookDTO;
import com.reading.reading.model.order.OrderBook;
import com.reading.reading.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public List<OrderBook> add(@RequestBody AddOrderBookRequest request) {
        return orderService.add(request);
    }

    @GetMapping()
    public List<OrderBookDTO> listByDateInterval(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return orderService.findByDateInterval(startDate, endDate).
                stream().map(OrderBook::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderBookDTO getById(@PathVariable Long id) {
        return orderService.findOneById(id).toDTO();
    }
}
