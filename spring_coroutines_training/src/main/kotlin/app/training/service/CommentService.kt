package app.training.service

import app.training.dto.CommentDto
import app.training.repository.CommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {

    suspend fun findByMemberId(id: Long): CommentDto {
        return withContext(Dispatchers.IO) {
            delay(1000)
            val comment = commentRepository.findByMemberId(id)

            CommentDto(
                id = comment.id !!,
                memberId = comment.memberId,
                boardId = comment.boardId,
                content = comment.content,
            )
        }
    }

}