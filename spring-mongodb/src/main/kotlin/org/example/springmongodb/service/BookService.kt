package org.example.springmongodb.service

import org.example.springmongodb.repository.Book
import org.example.springmongodb.repository.BookPrice
import org.example.springmongodb.repository.BookRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class BookService(
    private val bookRepository: BookRepository,
) {

    @Transactional
    fun create(name: String, author: String, bookPrice: Set<BookPrice>): Book {
        return bookRepository.save(Book(name, author, bookPrice))
    }

    @Transactional
    fun modify(id : String, name: String, author: String, bookPrice: Set<BookPrice>) {
        val book = bookRepository.findByIdOrNull(id)?: throw RuntimeException("ttt")
        book.modify(name, author, bookPrice)
        bookRepository.save(book)
    }

}