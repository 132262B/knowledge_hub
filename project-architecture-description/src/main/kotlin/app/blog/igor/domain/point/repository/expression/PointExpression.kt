package app.blog.igor.domain.point.repository.expression

import app.blog.igor.domain.point.model.QPoint.point
import java.time.LocalDateTime

object PointExpression {

    fun memberIdEq(memberId: Long?) = memberId?.let { point.memberId.eq(memberId) }
    fun startDateGoe(startDate: LocalDateTime?) = startDate?.let { point.createdAt.goe(startDate) }
    fun endDateLoe(endDate: LocalDateTime?) = endDate?.let { point.createdAt.loe(endDate) }
    fun eventIdEq(eventId: Long?) = eventId?.let { point.eventId.eq(eventId) }
}