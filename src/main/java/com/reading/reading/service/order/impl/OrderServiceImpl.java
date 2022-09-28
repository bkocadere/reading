package com.reading.reading.service.order.impl;

import com.reading.reading.api.request.AddOrderBookRequest;
import com.reading.reading.api.request.BasketBook;
import com.reading.reading.enums.Status;
import com.reading.reading.model.book.Book;
import com.reading.reading.model.book.BookStock;
import com.reading.reading.model.customer.Customer;
import com.reading.reading.model.order.OrderBook;
import com.reading.reading.repository.book.BookRepository;
import com.reading.reading.repository.book.BookStockRepository;
import com.reading.reading.repository.customer.CustomerRepository;
import com.reading.reading.repository.order.OrderBookRepository;
import com.reading.reading.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderBookRepository orderBookRepository;

    private final BookRepository bookRepository;

    private final BookStockRepository bookStockRepository;

    private final CustomerRepository customerRepository;

    @Override
    public Page<OrderBook> getOrderByCustomerId(Long id, Integer size, Integer page) {
        PageRequest pageRequest = PageRequest.of
                (page == null ? 0 : page, size == null ? 100 : size);
        return orderBookRepository.findByCustomerId(id, pageRequest);
    }

    @Override
    @Transactional
    public List<OrderBook> add(AddOrderBookRequest request) {
        List<OrderBook> orderBook = prepareOrderBookList(request);
        return orderBookRepository.saveAll(orderBook);
    }

    @Override
    public OrderBook findOneById(Long id) {
        return orderBookRepository.findById(id).orElseThrow();
    }

    @Override
    public List<OrderBook> findByDateInterval(LocalDate startDate, LocalDate endDate) {
        return orderBookRepository.findByOperatingDateBetween(startDate, endDate);
    }


    private List<OrderBook> prepareOrderBookList(AddOrderBookRequest request) {
        checkUniqueBookId(request.getBasketBookList());
        String reference = UUID.randomUUID().toString();
        return request.getBasketBookList().stream().map(basketBook -> prepareOrderBook(basketBook, request, reference)).collect(Collectors.toList());
    }

    private void checkUniqueBookId(List<BasketBook> basketBookList) {
        Set<Long> idSet = basketBookList.stream().map(BasketBook::getBookId).collect(Collectors.toSet());
        if (idSet.size() != basketBookList.size()) {
            throw new RuntimeException("Book id must be unique");
        }
    }

    private OrderBook prepareOrderBook(BasketBook basketBook, AddOrderBookRequest request, String reference) {
        OrderBook orderBook = new OrderBook();
        orderBook.setBook(checkBook(basketBook.getBookId()));
        orderBook.setQuantity(checkAndLockQuantity(basketBook));
        orderBook.setAmount(orderBook.getBook().getAmount());
        orderBook.setCustomer(checkCustomer(request.getCustomerId()));
        orderBook.setBasketReference(reference);
        orderBook.setExecutionDate(OffsetDateTime.now());
        orderBook.setOperatingDate(LocalDate.now());
        return orderBook;
    }

    private Customer checkCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        if (!customer.getStatus().equals(Status.ACTIVE)) {
            throw new RuntimeException("Customer is not active");
        }
        return customer;
    }

    private int checkAndLockQuantity(BasketBook basketBook) {
        // Lock with query
        BookStock bookStock = bookStockRepository.findByBookId(basketBook.getBookId()).orElseThrow();
        int resultQuantity = bookStock.getQuantity() - basketBook.getQuantity();
        if (resultQuantity < 0) {
            throw new RuntimeException("Quantity not enough");
        }

        bookStock.setQuantity(resultQuantity);
        return basketBook.getQuantity();
    }

    private Book checkBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow();
    }
}
