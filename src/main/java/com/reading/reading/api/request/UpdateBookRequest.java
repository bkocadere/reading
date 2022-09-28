package com.reading.reading.api.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UpdateBookRequest {
    @NotNull
    @Min(0)
    private Integer quantity;
}
