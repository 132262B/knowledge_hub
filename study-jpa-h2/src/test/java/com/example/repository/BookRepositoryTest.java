package com.example.repository;

import com.example.domain.Book;
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


}