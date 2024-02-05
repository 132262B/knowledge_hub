package app.learning.order

import app.learning.storage.db.core.order.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderReadService(
    private val orderRepository: OrderRepository,
) {

    fun find() = orderRepository.findAll()
        .let { it ->
            it.map {
                OrderDto(
                    it.id !!,
                    it.memberId,
                    it.productId,
                    it.quantity,
                )
            }
        }


}