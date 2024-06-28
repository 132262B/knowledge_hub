package org.example.usecase.controller

import org.example.usecase.dto.NewOrder
import org.example.usecase.dto.OrderDto
import org.example.usecase.entity.OrderEntity
import org.example.usecase.usecase.CreateOrderUsecase
import org.example.usecase.usecase.FindOrderUsecase
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val createOrderUsecase: CreateOrderUsecase,
    private val findOrderUsecase: FindOrderUsecase,
) {

    @GetMapping("/api/order/{id}")
    fun getOrder(
        @PathVariable id: Long
    ): OrderDto = findOrderUsecase.execute(id)


    @PostMapping("/api/order")
    fun create(): OrderDto = createOrderUsecase
        .execute(NewOrder(OrderEntity.Status.CANCELLED))
        .let(findOrderUsecase::execute)

}