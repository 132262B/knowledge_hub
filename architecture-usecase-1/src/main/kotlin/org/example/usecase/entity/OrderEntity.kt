package org.example.usecase.entity

import jakarta.persistence.*

@Entity
@Table(name = "orders")
class OrderEntity(

    @Column(columnDefinition = "varchar(50)")
    @Enumerated(EnumType.STRING)
    var status: Status = Status.SUBMITTED,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

) {

    enum class Status {
        SUBMITTED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED,
    }

}