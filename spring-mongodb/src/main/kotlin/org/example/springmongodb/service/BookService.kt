package org.example.springmongodb.service

import org.example.springmongodb.repository.Book
import org.example.springmongodb.repository.BookPrice
import org.example.springmongodb.repository.BookRepository
import org.springframework.stereotype.Service


@Service
class BookService(
    private val bookRepository: BookRepository,
) {

    fun create(name: String, author: String, bookPrice: Set<BookPrice>): Book {
        return bookRepository.save(Book(name, author, bookPrice))
    }

}