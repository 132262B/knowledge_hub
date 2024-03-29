package app.learning.customer.usecase

import app.learning.customer.mapper.OrderMapper
import app.learning.customer.request.CreateOrderRequest
import app.learning.customer.request.ModifyQuantityRequest
import app.learning.customer.response.OrderResponse
import app.learning.enumerated.OrderStatus
import app.learning.order.OrderCreateService
import app.learning.order.OrderModifyService
import app.learning.order.OrderReadService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class OrderUseCase(
    private val orderCreateService: OrderCreateService,
    private val orderReadService: OrderReadService,
    private val orderModifyService: OrderModifyService,
) {

    @Transactional
    fun create(memberId: Long, request: CreateOrderRequest): Long? {

        val orderId = orderCreateService.create(
            memberId,
            request.toNewOrder(),
        )

        return orderId
    }


    @Transactional(readOnly = true)
    fun findList(): List<OrderResponse> =
        orderReadService.find().let {
            OrderMapper.orderResponseOf(it)
        }

    @Transactional
    fun modifyQuantity(orderId: Long, request: ModifyQuantityRequest) =
        orderReadService.isStatusCheck(orderId, OrderStatus.quantityModifiable())
            .apply {
                orderModifyService.modifyQuantity(orderId, request.quantity)
            }


}