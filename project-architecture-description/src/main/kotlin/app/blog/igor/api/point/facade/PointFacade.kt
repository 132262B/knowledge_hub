package app.blog.igor.api.point.facade

import app.blog.igor.api.point.mapper.PointMapper
import app.blog.igor.api.point.vo.MyPointResponse
import app.blog.igor.domain.point.service.PointService
import app.blog.igor.global.annotation.Facade

@Facade
class PointFacade(
    private val pointService: PointService,
    private val pointMapper: PointMapper,
) {

    fun findMyPoint(
        memberId: Long,
    ): List<MyPointResponse> {
        return pointMapper.pointDtoToMyPointResponse(
            pointService.findPoint(
                memberId = memberId,
            )
        )
    }
}