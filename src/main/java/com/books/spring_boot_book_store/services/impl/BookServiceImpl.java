package com.books.spring_boot_book_store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.spring_boot_book_store.domain.Book;
import com.books.spring_boot_book_store.domain.BookEntity;
import com.books.spring_boot_book_store.repositories.BookRepository;
import com.books.spring_boot_book_store.services.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(final Book book) {
        final BookEntity bookEntity = bookToBookEntity(book);
        final BookEntity savedBookEntity = bookRepository.save(bookEntity);
        return BookEntityToBook(savedBookEntity);
    }

    private BookEntity bookToBookEntity(final Book book) {
        return BookEntity.builder()
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .title(book.getTitle())
                .build();
    }

    private Book BookEntityToBook(final BookEntity book) {
        return Book.builder()
                .isbn(book.getIsbn())
                .author(book.getAuthor())
                .title(book.getTitle())
                .build();
    }
}
