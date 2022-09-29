package com.reading.reading.service.order;

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
import com.reading.reading.service.order.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    private static final Long CUSTOMER_ID = 4L;
    private static final Long BOOK_ID = 5L;
    private static final Integer QUANTITY = 8;

    private static final Integer ORDER_QUANTITY = 2;
    private static final BigDecimal AMOUNT = BigDecimal.TEN;
    private OrderService service;

    @Mock
    private OrderBookRepository orderBookRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookStockRepository bookStockRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void givenValidRequestWhenAddThenReturnSuccess() {
        // Arrange
        AddOrderBookRequest request = prepareOrderRequest();
        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(prepareBook()));
        when(bookStockRepository.findByBookId(BOOK_ID)).thenReturn(Optional.of(prepareBookStock()));
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(prepareCustomer()));
        // Act
        List<OrderBook> orderBooks = service.add(request);

        // Asserts
        OrderBook orderBook = orderBooks.get(0);
        assertEquals(orderBook.getBook().getId(), BOOK_ID);
        assertEquals(orderBook.getAmount(), AMOUNT);

        verify(orderBookRepository).saveAll(orderBooks);
    }

    private Customer prepareCustomer() {
        Customer customer = new Customer();
        customer.setStatus(Status.ACTIVE);
        return customer;
    }

    private BookStock prepareBookStock() {
        BookStock bookStock = new BookStock();
        bookStock.setQuantity(QUANTITY);
        return bookStock;
    }

    private Book prepareBook() {
        Book book = new Book();
        book.setId(BOOK_ID);
        book.setAmount(AMOUNT);
        return book;
    }

    private AddOrderBookRequest prepareOrderRequest() {
        AddOrderBookRequest request = new AddOrderBookRequest();
        request.setBasketBookList(prepareBasketBookList());
        request.setCustomerId(CUSTOMER_ID);
        return request;
    }

    private List<BasketBook> prepareBasketBookList() {
        BasketBook basketBook = new BasketBook();
        basketBook.setBookId(BOOK_ID);
        basketBook.setQuantity(ORDER_QUANTITY);
        return List.of(basketBook);
    }

    @BeforeEach
    public void setup() {
        service = new OrderServiceImpl(orderBookRepository, bookRepository, bookStockRepository, customerRepository);
    }
}
