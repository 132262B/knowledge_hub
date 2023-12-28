package app.training.service

import app.training.dto.BoardDto
import app.training.repository.BoardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val boardRepository: BoardRepository,
) {

    suspend fun findByMemberId(memberId: Long): BoardDto {
        return withContext(Dispatchers.IO) {
            delay(4000)
            val board = boardRepository.findByMemberId(memberId)
            BoardDto(
                id = board.id !!,
                memberId = board.memberId,
                title = board.title,
                content = board.content,
            )
        }

    }

}