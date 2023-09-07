package app.blog.igor.api.point.facade

import app.blog.igor.api.point.mapper.PointMapper
import app.blog.igor.api.point.vo.PointHistoryResponse
import app.blog.igor.domain.point.service.PointService
import app.blog.igor.global.annotation.Facade
import java.time.LocalDateTime

@Facade
class PointAdminFacade(
    private val pointService: PointService,
    private val pointMapper: PointMapper,
) {
    fun findPointList(
        startDate: LocalDateTime?,
        endDate: LocalDateTime?,
        memberId: Long?,
        eventId: Long?
    ): List<PointHistoryResponse> {

        return pointMapper.pointDtoToPointHistoryResponse(
            pointService.findPoint(
                startDate = startDate,
                endDate = endDate,
                memberId = memberId,
                eventId = eventId,
            )
        )
    }

}