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
                .author("남궁성")
                .build();

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());

    }


}