package org.example.springmongodb.repository

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "book")
open class Book(
    name: String,
    author: String,
) {
    @Id
    var id: String? = null
        protected set

    var name = name
        protected set

    var author = author
        protected set


}