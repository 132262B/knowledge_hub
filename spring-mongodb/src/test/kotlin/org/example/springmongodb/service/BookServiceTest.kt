package org.example.springmongodb.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest {

    @Autowired
    private lateinit var bookService : BookService

    @Test
    fun createTest() {
        // given
        val name = "자바 ORM 표준 JPA 프로그래밍"
        val author = "김영환"

        // when
        val book = bookService.create(name, author)

        // then
        assertThat(book.name).isEqualTo(name)
        assertThat(book.author).isEqualTo(author)

    }
}