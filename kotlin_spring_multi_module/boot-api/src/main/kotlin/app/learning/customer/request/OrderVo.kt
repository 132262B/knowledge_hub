package app.learning.customer.request

import app.learning.order.NewOrder

data class CreateOrderRequest(
    val productId: Long,
    val quantity: Int,
) {

    fun toNewOrder(): NewOrder {
        return NewOrder(
            this.productId,
            this.quantity,
        )
    }

}