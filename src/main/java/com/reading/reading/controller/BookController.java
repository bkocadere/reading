package com.reading.reading.controller;

import com.reading.reading.api.request.AddBookRequest;
import com.reading.reading.api.request.UpdateBookRequest;
import com.reading.reading.api.response.book.BookDTO;
import com.reading.reading.model.book.Book;
import com.reading.reading.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public BookDTO add(@RequestBody AddBookRequest request) {
        return bookService.add(request).toDTO();
    }

    @PutMapping("{id}")
    public BookDTO update(@RequestParam Long id, @RequestBody UpdateBookRequest request){
        return bookService.update(id, request).toDTO();
    }

}
