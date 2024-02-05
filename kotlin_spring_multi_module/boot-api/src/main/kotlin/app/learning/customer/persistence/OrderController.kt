package app.learning.customer.persistence


import app.learning.customer.request.CreateOrderRequest
import app.learning.customer.request.ModifyQuantityRequest
import app.learning.customer.response.OrderResponse
import app.learning.customer.usecase.OrderUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class OrderController(
    private val useCase: OrderUseCase,
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/v1/order")
    fun create(
        @RequestBody @Valid request: CreateOrderRequest
    ): Long = useCase.create(1, request)

    @GetMapping("/v1/order")
    fun findList(): List<OrderResponse> = useCase.findList()

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/v1/order/{orderId}/quantity")
    fun modifyQuantity(
        @PathVariable orderId: Long,
        @RequestBody request : ModifyQuantityRequest
    ) = useCase.modifyQuantity(orderId, request)

}