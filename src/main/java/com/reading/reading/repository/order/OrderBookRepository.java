package com.reading.reading.repository.order;

import com.reading.reading.api.dto.StatisticQueryDTO;
import com.reading.reading.model.order.OrderBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {
    Page<OrderBook> findByCustomerId(Long id, Pageable pageable);

    /*@Query("Select new com.reading.reading.api.dto.StatisticQueryDTO(operatingDate, basketReference, sum(quantity) as totalQuantity," +
            " sum(amount*quantity) as totalAmount) From OrderBook o Where o.customer.id =:customerId " +
            "And operatingDate >= :fromDate GROUP BY operatingDate, basketReference")
    List<StatisticQueryDTO> findByCustomerAndStatistics(long customerId, LocalDate fromDate);*/

    @Query("Select new com.reading.reading.api.dto.StatisticQueryDTO(operatingDate, basketReference, sum(quantity) as totalQuantity," +
            " sum(amount*quantity) as totalAmount) From OrderBook o  Where o.customer.id =:customerId " +
            "AND operatingDate > :fromDate " +
            " GROUP BY operatingDate, basketReference")
    List<StatisticQueryDTO> findByCustomerAndStatistics(long customerId, LocalDate fromDate);

    List<OrderBook> findByOperatingDateBetween(LocalDate fromDate, LocalDate localDate);
}
