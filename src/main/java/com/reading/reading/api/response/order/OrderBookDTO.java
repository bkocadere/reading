package com.reading.reading.api.response.order;

import com.reading.reading.api.response.book.BookDTO;
import com.reading.reading.api.response.customer.CustomerDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
public class OrderBookDTO {
    private BookDTO book;

    private CustomerDTO customer;

    private String basketReference;

    private BigDecimal amount;

    private int quantity;

    private LocalDate operatingDate;

    private OffsetDateTime executionDate;
}
