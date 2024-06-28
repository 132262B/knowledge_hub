package org.example.usecase.dto

import org.example.usecase.entity.OrderEntity

data class NewOrder(
    val status: OrderEntity.Status,
)