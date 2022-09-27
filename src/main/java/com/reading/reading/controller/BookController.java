package com.reading.reading.controller;

import com.reading.reading.api.request.AddBookRequest;
import com.reading.reading.model.book.Book;
import com.reading.reading.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public Book add(@RequestBody AddBookRequest request) {
        return bookService.add(request);
    }

}
