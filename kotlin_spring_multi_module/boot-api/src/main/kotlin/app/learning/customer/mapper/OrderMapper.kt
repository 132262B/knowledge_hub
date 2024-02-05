package app.learning.customer.mapper

import app.learning.customer.response.OrderResponse
import app.learning.order.OrderDto

object OrderMapper {

    fun orderResponseOf(
        orderDtos : List<OrderDto>
    ) : List<OrderResponse> = orderDtos.map {
        OrderResponse(
            id = it.id,
            quantity = it.quantity,
        )
    }


}