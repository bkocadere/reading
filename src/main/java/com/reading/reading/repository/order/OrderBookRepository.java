package com.reading.reading.repository.order;

import com.reading.reading.model.order.OrderBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {
    Page<OrderBook> findByCustomerId(Long id, Pageable pageable);
}
