package com.reading.reading.service.book.impl;

import com.reading.reading.api.request.AddBookRequest;
import com.reading.reading.model.book.Book;
import com.reading.reading.model.book.BookStock;
import com.reading.reading.repository.book.BookRepository;
import com.reading.reading.repository.book.BookStockRepository;
import com.reading.reading.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookStockRepository bookStockRepository;

    @Override
    public Book add(AddBookRequest request) {
        Book book = prepareBook(request);
        BookStock bookStock = prepareBookStock(book, request);

        bookStockRepository.save(bookStock);
        return book;
    }

    private BookStock prepareBookStock(Book book, AddBookRequest request) {
        BookStock bookStock = new BookStock();
        bookStock.setBook(book);
        bookStock.setQuantity(request.getQuantity());
        return bookStock;
    }

    private Book prepareBook(AddBookRequest request) {
        Book book = new Book();
        book.setAmount(request.getAmount());
        book.setName(request.getName());
        book.setReference(UUID.randomUUID().toString());
        return book;
    }
}
