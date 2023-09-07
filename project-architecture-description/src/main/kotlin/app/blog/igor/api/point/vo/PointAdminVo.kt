package app.blog.igor.api.point.vo

import java.math.BigDecimal

data class PointHistoryResponse(
    val pointId : Long,
    val memberId : Long,
    val reasonEarning : String,
    val reserveAmount : BigDecimal,
    val eventId : Long?,
)