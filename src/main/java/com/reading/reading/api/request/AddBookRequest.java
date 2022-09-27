package com.reading.reading.api.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AddBookRequest {
    @NotNull
    private String name;

    @NotNull
    @Min(0)
    private BigDecimal amount;

    @NotNull
    @Min(0)
    private Integer quantity;
}
