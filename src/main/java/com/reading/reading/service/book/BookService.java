package com.reading.reading.service.book;

import com.reading.reading.api.request.AddBookRequest;
import com.reading.reading.api.request.UpdateBookRequest;
import com.reading.reading.model.book.Book;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface BookService {
    Book add(@Valid AddBookRequest request);

    Book update(@NotNull Long id, @Valid UpdateBookRequest request);
}
