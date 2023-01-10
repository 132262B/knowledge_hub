package com.example.repository;

import com.example.domain.Book;
import com.example.domain.dto.BookStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BookRepositoryTest {


    @Autowired
    private BookRepository bookRepository;

    @Test
    void bookSaveTest() {
        Book book = Book.builder()
                .name("자바의 정석")
                .authorId(1L)
                .build();

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());

    }


    @Test
    void bookStatusSaveTest() {
        Book book = Book.builder()
                .name("자바의 정석일까?")
                .authorId(1L)
                .status(new BookStatus(200))
                .build();

        bookRepository.save(book);

        bookRepository.findAll().forEach(System.out::println);

    }

}