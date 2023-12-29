package app.training.usecase

import app.training.dto.Banner
import app.training.dto.BoardDto
import app.training.dto.CommentDto
import app.training.dto.MemberDto
import app.training.service.BoardService
import app.training.service.CommentService
import app.training.service.MemberService
import kotlinx.coroutines.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.ServerResponse.async

@Service
class ContentUseCase(
    private val boardService : BoardService,
    private val commentService : CommentService,
    private val memberService : MemberService,
) {
    @Transactional(readOnly = true)
    suspend fun findMyContent(id: Long) {
        coroutineScope {

            println("코루틴 시작 : ${Thread.currentThread().name}")
            val memberDto: Deferred<MemberDto> = async {
                memberService.findMyInfo(id)
            }

            val boardDto: Deferred<List<BoardDto>> = async {
                boardService.findByMemberId(id)
            }

            val commentDto: Deferred<List<CommentDto>> = async {
                commentService.findByMemberId(id)
            }

            println(memberDto.await())
            println(boardDto.await())
            println(commentDto.await())
            println("코루틴 종료 : ${Thread.currentThread().name}")
        }

    }
}