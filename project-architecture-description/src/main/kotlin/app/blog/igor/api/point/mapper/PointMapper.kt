package app.blog.igor.api.point.mapper

import app.blog.igor.api.point.vo.MyPointResponse
import app.blog.igor.api.point.vo.PointHistoryResponse
import app.blog.igor.domain.point.dto.PointDto
import app.blog.igor.global.annotation.Mapper

@Mapper
class PointMapper {

    fun pointDtoToPointHistoryResponse(findPoint: List<PointDto>): List<PointHistoryResponse> {
        return findPoint.map {
            PointHistoryResponse(
                pointId = it.pointId,
                memberId = it.memberId,
                reasonEarning = it.reasonEarning,
                reserveAmount = it.reserveAmount,
                eventId = it.eventId,
            )
        }
    }

    fun pointDtoToMyPointResponse(findPoint: List<PointDto>): List<MyPointResponse> {
        return findPoint.map {
            MyPointResponse(
                pointId = it.pointId,
                reasonEarning = it.reasonEarning,
                reserveAmount = it.reserveAmount,
            )
        }
    }

}