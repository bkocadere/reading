package com.reading.reading.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class StatisticQueryDTO {
    private LocalDate operatingDate;

    private String basketReference;

    private int totalQuantity;

    private BigDecimal totalAmount;

    public StatisticQueryDTO(LocalDate operatingDate, String basketReference, long totalQuantity, BigDecimal totalAmount) {
        this.operatingDate = operatingDate;
        this.basketReference = basketReference;
        this.totalQuantity = (int) totalQuantity;
        this.totalAmount = totalAmount;
    }
}
