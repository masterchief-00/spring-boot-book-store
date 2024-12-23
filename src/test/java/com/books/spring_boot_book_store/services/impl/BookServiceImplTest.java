package com.books.spring_boot_book_store.services.impl;

import static com.books.spring_boot_book_store.TestData.testBook;
import static com.books.spring_boot_book_store.TestData.testBookEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

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

        final Book result = underTest.createBook(book);
        assertEquals(book, result);
    }

}
