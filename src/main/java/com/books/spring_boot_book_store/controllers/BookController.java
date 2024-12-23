package com.books.spring_boot_book_store.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.books.spring_boot_book_store.domain.Book;
import com.books.spring_boot_book_store.services.BookService;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<Book> createBook(@PathVariable String isbn, @RequestBody final Book book) {
        book.setIsbn(isbn);
        final Book saveBook = bookService.createBook(book);
        final ResponseEntity<Book> response = new ResponseEntity<Book>(saveBook, HttpStatus.CREATED);
        return response;
    }
}
