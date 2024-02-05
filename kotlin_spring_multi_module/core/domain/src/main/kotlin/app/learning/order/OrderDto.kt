package app.learning.order

data class OrderDto(
    val id : Long,
    val memberId : Long,
    val productId: Long,
    val quantity : Int,
)