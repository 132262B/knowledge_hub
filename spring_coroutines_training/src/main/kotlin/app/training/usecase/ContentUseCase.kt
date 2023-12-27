package app.training.usecase

import app.training.service.BoardService
import app.training.service.CommentService
import app.training.service.MemberService
import org.springframework.stereotype.Service

@Service
class ContentUseCase(
    private val boardService : BoardService,
    private val commentService : CommentService,
    private val memberService : MemberService,
) {
    suspend fun findMyContent(id: Long) {
        memberService.findMyInfo(id)
//        boardService.findByMemberId(id)
//        commentService.findByMemberId(id)
    }
}