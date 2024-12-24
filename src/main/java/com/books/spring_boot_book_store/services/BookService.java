package com.books.spring_boot_book_store.services;

import java.util.List;
import java.util.Optional;

import com.books.spring_boot_book_store.domain.Book;

public interface BookService {
    Book save(Book book);

    Optional<Book> findBookByIsbn(String isbn);

    List<Book> listBooks();

    void deleteBookByIsbn(String isbn);

    boolean isBookExists(Book book);
}
