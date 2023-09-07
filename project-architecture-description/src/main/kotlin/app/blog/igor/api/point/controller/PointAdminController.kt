package app.blog.igor.api.point.controller

import app.blog.igor.api.point.facade.PointAdminFacade
import app.blog.igor.api.point.vo.PointHistoryResponse
import app.blog.igor.global.util.toEndOfDay
import app.blog.igor.global.util.toStartOfDay
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/admin/points")
class PointAdminController(
    private val pointAdminFacade: PointAdminFacade
) {

    @GetMapping
    fun findPoint(
        @RequestParam startDate: LocalDate?,
        @RequestParam endDate: LocalDate?,
        @RequestParam memberId: Long?,
        @RequestParam eventId: Long?,
    ): ResponseEntity<List<PointHistoryResponse>> {
        return ResponseEntity.ok(
            pointAdminFacade.findPointList(
                startDate = startDate.toStartOfDay(),
                endDate = endDate.toEndOfDay(),
                memberId = memberId,
                eventId = eventId,
            )
        )
    }

}