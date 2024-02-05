package app.learning.storage.db.core.order;

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull


fun OrderRepository.findOrderById(id: Long) =
    findByIdOrNull(id) ?: throw RuntimeException(
        "order가 존재하지 않습니다. (orderId : ${id})"
    )
interface OrderRepository : JpaRepository<Order, Long>