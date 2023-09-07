package app.blog.igor.domain.point.repository

import app.blog.igor.domain.point.dto.PointDto
import app.blog.igor.domain.point.dto.QPointDto
import app.blog.igor.domain.point.model.QPoint.point
import app.blog.igor.domain.point.repository.expression.PointExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class PointQueryRepository(
    private val queryFactory: JPAQueryFactory
) {

    fun findQueryPoint(
        startDate: LocalDateTime?,
        endDate: LocalDateTime?,
        memberId: Long?,
        eventId: Long?
    ): List<PointDto> {
        return queryFactory
            .select(
                QPointDto(
                    point.id,
                    point.memberId,
                    point.reasonEarning,
                    point.reserveAmount,
                    point.eventId,
                )
            )
            .from(point)
            .where(
                PointExpression.memberIdEq(memberId),
                PointExpression.eventIdEq(eventId),
                PointExpression.startDateGoe(startDate),
                PointExpression.endDateLoe(endDate),
            )
            .fetch()
    }

}