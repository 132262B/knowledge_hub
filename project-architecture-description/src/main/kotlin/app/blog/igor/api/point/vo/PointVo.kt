package app.blog.igor.api.point.vo

import java.math.BigDecimal

data class MyPointResponse(
    val pointId : Long,
    val reasonEarning : String,
    val reserveAmount : BigDecimal,
)