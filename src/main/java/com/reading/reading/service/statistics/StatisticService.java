package com.reading.reading.service.statistics;

import com.reading.reading.api.response.statistic.StatisticResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface StatisticService {
    List<StatisticResponseDTO> generate(Long customerId, LocalDate fromDate);
}
