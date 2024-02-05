package app.learning.order

import app.learning.enumerated.OrderStatus
import app.learning.storage.db.core.order.OrderRepository
import app.learning.storage.db.core.order.findOrderById
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    @Transactional(readOnly = true)
    fun isStatusCheck(orderId : Long, quantityModifiable: OrderStatus) {
        val orderStatus = orderRepository.findOrderById(orderId).status

        if(orderStatus != quantityModifiable) {
            throw RuntimeException("수정할 수 없는 상태입니다.")
        }

    }



}