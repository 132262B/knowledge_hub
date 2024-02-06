package app.learning.storage.db.core.order

import app.learning.enumerated.OrderStatus
import app.learning.storage.db.core.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "orders")
class Order(
    val memberId: Long,
    val productId: Long,

    quantity: Int,

    status: OrderStatus = OrderStatus.SUBMITTED,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) : BaseEntity() {

    var quantity: Int = quantity
        protected set

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = status
        protected set


    fun modifyQuantity(quantity: Int) {
        this.quantity = quantity
    }
}