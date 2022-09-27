package com.reading.reading.service.book;

import com.reading.reading.api.request.AddBookRequest;
import com.reading.reading.model.book.Book;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface BookService {
    Book add(@Valid AddBookRequest request);
}
