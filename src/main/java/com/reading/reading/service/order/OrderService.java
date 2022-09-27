package com.reading.reading.service.order;

import com.reading.reading.api.request.AddOrderBookRequest;
import com.reading.reading.model.order.OrderBook;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface OrderService {
    Page<OrderBook> getOrderByCustomerId(@NotNull Long id, Integer size, Integer page);

    List<OrderBook> add(@Valid @NotNull AddOrderBookRequest request);
}
