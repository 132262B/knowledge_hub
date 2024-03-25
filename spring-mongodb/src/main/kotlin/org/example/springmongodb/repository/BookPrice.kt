package org.example.springmongodb.repository

import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document
open class BookPrice(
    priceType: BookPriceType,
    price: BigDecimal,
) {

    var priceType: BookPriceType = priceType
        protected set
    var price: BigDecimal = price
        protected set

}