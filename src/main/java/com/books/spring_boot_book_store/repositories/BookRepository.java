package com.books.spring_boot_book_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.books.spring_boot_book_store.domain.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {
    
}
