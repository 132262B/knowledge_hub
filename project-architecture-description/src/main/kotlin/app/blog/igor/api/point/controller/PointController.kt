package app.blog.igor.api.point.controller

import app.blog.igor.api.point.facade.PointFacade
import app.blog.igor.api.point.vo.MyPointResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/points")
class PointController(
    private val pointFacade : PointFacade
) {

    @GetMapping
    fun findPoint(
        // member 리졸버 선언됨
    ) : ResponseEntity<List<MyPointResponse>> {
        return ResponseEntity.ok(
            pointFacade.findMyPoint(
                memberId = 1L // member 리졸버에서 꺼내서 씀
            )
        )
    }

}