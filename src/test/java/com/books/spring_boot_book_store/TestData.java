package com.books.spring_boot_book_store;

import com.books.spring_boot_book_store.domain.Book;
import com.books.spring_boot_book_store.domain.BookEntity;

public class TestData {
    private TestData() {
    }

    public static Book testBook() {
        return Book.builder()
                .isbn("02345678")
                .author("Vriginia Woolf")
                .title("The Waves")
                .build();
    }

    public static BookEntity testBookEntity() {
        return BookEntity.builder()
                .isbn("02345678")
                .author("Vriginia Woolf")
                .title("The Waves")
                .build();
    }
}
