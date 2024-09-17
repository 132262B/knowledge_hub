package com.example

import jakarta.persistence.*


@Entity
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val quantity : Int,
    val customerId : Long,
    status : Status = Status.SUBMITTED
) {

    var status : Status = status
        protected set


    enum class Status {
        SUBMITTED,
        PAYMENT_COMPLETED,
        CANCEL,
    }

    override fun toString(): String {
        return "OrderEntity(id=$id, quantity=$quantity, customerId=$customerId, status=$status)"
    }
}