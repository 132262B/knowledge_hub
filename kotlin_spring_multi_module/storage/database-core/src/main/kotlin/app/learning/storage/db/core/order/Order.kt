package app.learning.storage.db.core.order

import app.learning.storage.db.core.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "orders")
class Order(
    val memberId : Long,
    val productId : Long,

    quantity : Int,

) : BaseEntity() {

    var quantity : Int = quantity
        protected set

}