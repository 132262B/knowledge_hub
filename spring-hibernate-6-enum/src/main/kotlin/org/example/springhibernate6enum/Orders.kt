package org.example.springhibernate6enum

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
class Orders(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    //@Column(columnDefinition = "varchar(50)")
    //@JdbcTypeCode(SqlTypes.VARCHAR)
    @Enumerated(EnumType.STRING)
    val status: Status,

) {
    enum class Status {
        ORDERED,
        CANCELLED,
    }
}