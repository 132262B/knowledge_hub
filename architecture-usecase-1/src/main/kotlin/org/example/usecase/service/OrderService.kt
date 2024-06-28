package org.example.usecase.service

import org.example.usecase.dto.NewOrder
import org.example.usecase.dto.OrderDto
import org.example.usecase.entity.OrderEntity
import org.example.usecase.repository.OrderRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {
    fun getOrder(id: Long): OrderDto = orderRepository
        .findByIdOrNull(id)
        ?.let {
            OrderDto(
                id = it.id,
                status = it.status,
            )
        }
        ?: throw IllegalArgumentException("Order not found")

    @Transactional
    fun createOrder(newOrder: NewOrder): Long {
        val order = orderRepository.save(
            OrderEntity(
                status = newOrder.status,
            )
        )

        return order.id
    }

}
