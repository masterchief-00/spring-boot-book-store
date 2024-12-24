package com.books.spring_boot_book_store.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<Book> createUpdateBook(@PathVariable String isbn, @RequestBody final Book book) {
        book.setIsbn(isbn);

        final boolean isBookExists = bookService.isBookExists(book);
        final Book saveBook = bookService.save(book);

        if (isBookExists) {
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<Book>(saveBook, HttpStatus.CREATED);
        }
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<Book> RetrieveBook(@PathVariable String isbn) {
        final Optional<Book> foundBook = bookService.findBookByIsbn(isbn);

        return foundBook.map(book -> new ResponseEntity<Book>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<Book>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> ListBooks() {
        return new ResponseEntity<List<Book>>(bookService.listBooks(), HttpStatus.OK);
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity DeleteBook(@PathVariable String isbn) {
        bookService.deleteBookByIsbn(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
