package app.learning.order

import app.learning.storage.db.core.order.Order
import app.learning.storage.db.core.order.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderCreateService(
    private val orderRepository: OrderRepository,
) {
    @Transactional
    fun create(memberId: Long, newOrder: NewOrder): Long? =
        orderRepository.save(
            Order(
                memberId = memberId,
                productId = newOrder.productId,
                quantity = newOrder.quantity,
            )
        ).id

}