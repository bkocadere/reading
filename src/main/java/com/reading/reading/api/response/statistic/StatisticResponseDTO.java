package com.reading.reading.api.response.statistic;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Month;

@Data
public class StatisticResponseDTO {
    private Month month;

    private int year;
    private int totalBookCount;
    private BigDecimal totalPurchaseAmount;

    public StatisticResponseDTO(Month month, int totalBookCount, BigDecimal totalPurchaseAmount, int year) {
        this.month = month;
        this.totalBookCount = totalBookCount;
        this.totalPurchaseAmount = totalPurchaseAmount;
        this.year = year;
    }
}
