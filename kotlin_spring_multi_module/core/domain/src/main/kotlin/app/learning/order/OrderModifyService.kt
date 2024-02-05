package app.learning.order

import app.learning.storage.db.core.order.OrderRepository
import app.learning.storage.db.core.order.findOrderById
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderModifyService(
    private val orderRepository: OrderRepository,
) {
    @Transactional
    fun modifyQuantity(orderId: Long, quantity: Int) {
        val order = orderRepository.findOrderById(orderId)
        order.modifyQuantity(quantity)
    }


}