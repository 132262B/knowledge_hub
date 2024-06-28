package org.example.usecase.usecase

import org.example.usecase.dto.OrderDto
import org.example.usecase.service.OrderService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FindOrderUsecase(
    private val orderService: OrderService,
) {

    fun execute(id: Long) : OrderDto {
        throw RuntimeException("test")
        return orderService.getOrder(id)
    }

}