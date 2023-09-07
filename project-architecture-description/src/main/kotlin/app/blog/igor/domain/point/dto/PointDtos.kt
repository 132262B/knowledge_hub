package app.blog.igor.domain.point.dto

import com.querydsl.core.annotations.QueryProjection
import java.math.BigDecimal

data class PointDto @QueryProjection constructor(
    val pointId : Long,
    val memberId : Long,
    val reasonEarning : String,
    val reserveAmount : BigDecimal,
    val eventId : Long?,
)