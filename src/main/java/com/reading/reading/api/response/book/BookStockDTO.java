package com.reading.reading.api.response.book;

import lombok.Data;

@Data
public class BookStockDTO {
    private BookDTO book;

    private int quantity;
}
