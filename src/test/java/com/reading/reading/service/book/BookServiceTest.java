package com.reading.reading.service.book;

import com.reading.reading.api.request.AddBookRequest;
import com.reading.reading.api.request.UpdateBookRequest;
import com.reading.reading.model.book.Book;
import com.reading.reading.model.book.BookStock;
import com.reading.reading.repository.book.BookRepository;
import com.reading.reading.repository.book.BookStockRepository;
import com.reading.reading.service.book.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    private static final String BOOK_NAME = "name";
    private static final Integer QUANTITY = 8;
    private static final Long BOOK_ID = 5L;
    private BookService service;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookStockRepository bookStockRepository;

    @Test
    public void givenValidRequestWhenAddThenReturnSuccess() {
        // Arrange
        AddBookRequest request = prepareAddRequest();

        // Act
        Book book = service.add(request);

        // Asserts
        ArgumentCaptor<BookStock> captor = ArgumentCaptor.forClass(BookStock.class);
        verify(bookStockRepository).save(captor.capture());
        BookStock capturedItem = captor.getValue();
        assertEquals(capturedItem.getQuantity(), request.getQuantity());
        assertEquals(capturedItem.getBook(), book);

        assertEquals(book.getAmount(), request.getAmount());
        assertEquals(book.getName(), request.getName());
        assertNotNull(book.getReference());
    }

    @Test
    public void givenValidRequestWhenUpdateThenReturnSuccess() {
        // Arrange
        UpdateBookRequest request = prepareUpdateRequest();
        when(bookStockRepository.findByBookId(BOOK_ID)).thenReturn(Optional.of(new BookStock()));
        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(prepareBook()));

        // Act
        Book book = service.update(BOOK_ID, request);

        // Asserts
        verify(bookStockRepository).findByBookId(BOOK_ID);
        verify(bookRepository).findById(BOOK_ID);

        assertEquals(book.getId(), BOOK_ID);
    }

    private Book prepareBook() {
        Book book = new Book();
        book.setId(BOOK_ID);
        return book;
    }

    private UpdateBookRequest prepareUpdateRequest() {
        UpdateBookRequest request = new UpdateBookRequest();
        request.setQuantity(QUANTITY);
        return request;
    }

    private AddBookRequest prepareAddRequest() {
        AddBookRequest request = new AddBookRequest();
        request.setAmount(BigDecimal.TEN);
        request.setName(BOOK_NAME);
        request.setQuantity(QUANTITY);
        return request;
    }

    @BeforeEach
    public void setup() {
        service = new BookServiceImpl(bookRepository, bookStockRepository);
    }
}
