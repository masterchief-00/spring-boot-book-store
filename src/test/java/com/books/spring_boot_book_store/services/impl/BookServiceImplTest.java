package com.books.spring_boot_book_store.services.impl;

import static com.books.spring_boot_book_store.TestData.testBook;
import static com.books.spring_boot_book_store.TestData.testBookEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.books.spring_boot_book_store.domain.Book;
import com.books.spring_boot_book_store.domain.BookEntity;
import com.books.spring_boot_book_store.repositories.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl underTest;

    @Test
    public void testThatBookIsSaved() {
        final Book book = testBook();

        final BookEntity bookEntity = testBookEntity();

        when(bookRepository.save(eq(bookEntity))).thenReturn(bookEntity);

        final Book result = underTest.save(book);
        assertEquals(book, result);
    }

    @Test
    public void testThatFindBookByIsbnReturnsEmptyWhenNoBook() {
        final String isbn = "1234567890";

        when(bookRepository.findById(eq(isbn))).thenReturn(Optional.empty());

        final Optional<Book> result = underTest.findBookByIsbn(isbn);
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testThatFindBookByIsbnReturnsBookWhenBookExists() {
        final Book book = testBook();

        final BookEntity bookEntity = testBookEntity();

        when(bookRepository.findById(eq(book.getIsbn()))).thenReturn(Optional.of(bookEntity));

        final Optional<Book> result = underTest.findBookByIsbn(book.getIsbn());
        assertEquals(Optional.of(book), result);
    }

    @Test
    public void testListBooksReturnsEmptyListWhenNoBooks() {
        when(bookRepository.findAll()).thenReturn(new ArrayList<BookEntity>());

        final List<Book> result = underTest.listBooks();
        assertEquals(0, result.size());
    }

    @Test
    public void testListBooksReturnsBooksWhenBooksExist() {
        final BookEntity bookEntity = testBookEntity();

        final List<BookEntity> bookEntities = new ArrayList<>();
        bookEntities.add(bookEntity);

        when(bookRepository.findAll()).thenReturn(bookEntities);

        final List<Book> result = underTest.listBooks();
        assertEquals(1, result.size());
    }

    @Test
    public void testIsBookExistsReturnsFalseWhenBookDoesNotExist() {
        final Book book = testBook();

        when(bookRepository.existsById(eq(book.getIsbn()))).thenReturn(false);

        final boolean result = underTest.isBookExists(book);
        assertEquals(false, result);
    }

    @Test
    public void testIsBookExistsReturnsTrueWhenBookExists() {
        final Book book = testBook();

        when(bookRepository.existsById(eq(book.getIsbn()))).thenReturn(true);

        final boolean result = underTest.isBookExists(book);
        assertEquals(true, result);
    }

    @Test
    public void testDeleteBookDeletesBook() {
        final Book book = testBook();

        underTest.deleteBookByIsbn(book.getIsbn());
        verify(bookRepository, times(1)).deleteById(eq(book.getIsbn()));
    }
}
