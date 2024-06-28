package org.example.usecase.usecase

import org.example.usecase.dto.NewOrder
import org.example.usecase.service.OrderService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateOrderUsecase(
    private val orderService: OrderService,
) {

    @Transactional
    fun execute(newOrder : NewOrder) : Long = orderService.createOrder(newOrder)

}