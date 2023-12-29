package app.training.service

import app.training.dto.CommentDto
import app.training.repository.CommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {

    @Transactional(readOnly = true)
    suspend fun findByMemberId(id: Long): List<CommentDto> {
        return withContext(Dispatchers.IO) {
            println("CommentService.findByMemberId 내부 : ${Thread.currentThread().name}")
            val comments = commentRepository.findByMemberId(id)
            delay(1000)

            comments.map {
                CommentDto(
                    id = it.id !!,
                    memberId = it.memberId,
                    boardId = it.boardId,
                    content = it.content,
                )
            }
        }
    }
}