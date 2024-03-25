package org.example.springmongodb.service

import org.assertj.core.api.Assertions.assertThat
import org.example.springmongodb.repository.BookPrice
import org.example.springmongodb.repository.BookPriceType
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal

@SpringBootTest
class BookServiceTest {

    @Autowired
    private lateinit var bookService: BookService

    @Test
    fun createTest() {
        // given
        val name = "자바 ORM 표준 JPA 프로그래밍"
        val author = "김영환"

        val bookPrices = setOf(
            BookPrice(BookPriceType.E_BOOK, BigDecimal("38700")),
            BookPrice(BookPriceType.PAPER_BOOK, BigDecimal("30960"))
        )

        // when
        val book = bookService.create(name, author, bookPrices)

        // then
        assertThat(book.name).isEqualTo(name)
        assertThat(book.author).isEqualTo(author)

        println(book.id)
    }

    @Test
    fun modifyTest() {
        // given
        val id = "66018b819b2d7b3f73220e97"
        val name = "베스트! 자바 ORM 표준 JPA 프로그래밍"
        val author = "킹영환"

        val bookPrices = setOf(
            BookPrice(BookPriceType.E_BOOK, BigDecimal("38700")),
            BookPrice(BookPriceType.PAPER_BOOK, BigDecimal("30960")),
            BookPrice(BookPriceType.ORIGINAL, BigDecimal("40000"))
        )

        // when
        bookService.modify(id, name, author, bookPrices)

    }
}