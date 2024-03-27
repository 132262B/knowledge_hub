package org.example.springmongodb.repository

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal


@Document(collection = "book")
open class Book(
    name: String,
    author: String,
    bookPrice: Set<BookPrice>
) {

    @Id
    var id: String? = null
        protected set

    var name = name
        protected set

    var author = author
        protected set

    var bookPrice = bookPrice
        protected set

    var toDayPrices : MutableList<BigDecimal> = mutableListOf()
        protected set

    fun modify(name: String, author: String, bookPrice: Set<BookPrice>) {
        this.name = name
        this.author = author
        this.bookPrice = bookPrice
    }

}