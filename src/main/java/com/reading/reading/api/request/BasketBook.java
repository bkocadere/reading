package com.reading.reading.api.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class BasketBook {
    @NotNull
    private Long bookId;

    @NotNull
    @Size(min = 1)
    private Integer quantity;
}
