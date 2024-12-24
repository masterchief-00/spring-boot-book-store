package com.books.spring_boot_book_store.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.books.spring_boot_book_store.domain.Book;
import com.books.spring_boot_book_store.domain.BookEntity;
import com.books.spring_boot_book_store.repositories.BookRepository;
import com.books.spring_boot_book_store.services.BookService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(final Book book) {
        final BookEntity bookEntity = bookToBookEntity(book);
        final BookEntity savedBookEntity = bookRepository.save(bookEntity);
        return BookEntityToBook(savedBookEntity);
    }

    @Override
    public Optional<Book> findBookByIsbn(final String isbn) {
        final Optional<BookEntity> foundBookEntity = bookRepository.findById(isbn);
        return foundBookEntity.map(book -> BookEntityToBook(book));
    }

    @Override
    public List<Book> listBooks() {
        final List<BookEntity> foundBooks = bookRepository.findAll();
        return foundBooks.stream().map(book -> BookEntityToBook(book)).collect(Collectors.toList());
    }

    @Override
    public boolean isBookExists(final Book book) {
        return bookRepository.existsById(book.getIsbn());
    }

    @Override
    public void deleteBookByIsbn(final String isbn) {
        try {
            bookRepository.deleteById(isbn);
        } catch (EmptyResultDataAccessException e) {
            log.debug("Attempted to delete a book that does not exist with isbn: {}", isbn);
        }

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
