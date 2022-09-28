package com.reading.reading.api.response.book;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDTO {
    private String name;

    private String reference;

    private BigDecimal amount;

}
