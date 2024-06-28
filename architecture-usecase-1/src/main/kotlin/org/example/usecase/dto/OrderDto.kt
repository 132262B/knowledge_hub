package org.example.usecase.dto

import org.example.usecase.entity.OrderEntity

data class OrderDto(
    val id: Long,
    val status: OrderEntity.Status,
)