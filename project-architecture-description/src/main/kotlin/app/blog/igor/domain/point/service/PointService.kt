package app.blog.igor.domain.point.service

import app.blog.igor.domain.point.dto.PointDto
import app.blog.igor.domain.point.repository.PointQueryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class PointService(
    private val pointQueryRepository: PointQueryRepository
) {
    fun findPoint(
        startDate: LocalDateTime? = null,
        endDate: LocalDateTime? = null,
        memberId: Long? = null,
        eventId: Long? = null,
    ): List<PointDto> {
        return pointQueryRepository.findQueryPoint(
            startDate = startDate,
            endDate = endDate,
            memberId = memberId,
            eventId = eventId,
        )
    }

}