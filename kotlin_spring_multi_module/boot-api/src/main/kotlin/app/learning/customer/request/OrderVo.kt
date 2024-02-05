package app.learning.customer.request

import app.learning.order.NewOrder
import jakarta.validation.constraints.Positive

data class CreateOrderRequest(
    val productId: Long,
    @field:Positive
    val quantity: Int,
) {

    fun toNewOrder(): NewOrder {
        return NewOrder(
            this.productId,
            this.quantity,
        )
    }

}


data class ModifyQuantityRequest(
    @field:Positive
    val quantity: Int,
)