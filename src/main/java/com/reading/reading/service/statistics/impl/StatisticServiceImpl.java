package com.reading.reading.service.statistics.impl;

import com.reading.reading.api.dto.StatisticQueryDTO;
import com.reading.reading.api.response.statistic.StatisticResponseDTO;
import com.reading.reading.repository.order.OrderBookRepository;
import com.reading.reading.service.statistics.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final OrderBookRepository orderBookRepository;

    @Override
    public List<StatisticResponseDTO> generate(Long customerId, LocalDate fromDate) {
        List<StatisticQueryDTO> statisticQueryDTOS = orderBookRepository.findByCustomerAndStatistics(customerId, fromDate);
        Map<YearMonth, Map<StatisticQueryDTO, Integer>> map =
                statisticQueryDTOS.stream()
                        .collect(Collectors.groupingBy(e -> YearMonth.from(e.getOperatingDate()), TreeMap::new,
                                Collectors.groupingBy(x -> x, Collectors.summingInt(StatisticQueryDTO::getTotalQuantity)))
                        );

        return map.entrySet().stream().map(x -> {
            BigDecimal totalAmount = x.getValue().keySet().stream().
                    map(StatisticQueryDTO::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            int totalQuantity = x.getValue().values().stream().mapToInt(integer -> integer).sum();

            return new StatisticResponseDTO(x.getKey().getMonth(), totalQuantity, totalAmount, x.getKey().getYear());
        }).toList();

    }
}
