package com.reading.reading.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AddOrderBookRequest {
    @NotEmpty
    private List<BasketBook> basketBookList;

    @NotNull
    private Long customerId;

}
