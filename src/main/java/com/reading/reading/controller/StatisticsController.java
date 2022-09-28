package com.reading.reading.controller;

import com.reading.reading.api.response.statistic.StatisticResponseDTO;
import com.reading.reading.service.statistics.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticService statisticService;

    @GetMapping("generate")
    public List<StatisticResponseDTO> generate(@RequestParam Long customerId, @RequestParam LocalDate fromDate) {
        return statisticService.generate(customerId, fromDate);
    }
}
