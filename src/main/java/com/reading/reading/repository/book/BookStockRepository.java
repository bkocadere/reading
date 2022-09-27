package com.reading.reading.repository.book;

import com.reading.reading.model.book.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface BookStockRepository extends JpaRepository<BookStock, Long> {
    @Lock(LockModeType.OPTIMISTIC)
    Optional<BookStock> findByBookId(long id);
}
