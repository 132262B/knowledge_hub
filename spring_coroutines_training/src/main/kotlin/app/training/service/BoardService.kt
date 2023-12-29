package app.training.service

import app.training.dto.BoardDto
import app.training.repository.BoardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(
    private val boardRepository: BoardRepository,
) {

    @Transactional(readOnly = true)
    suspend fun findByMemberId(memberId: Long): List<BoardDto> {
        return withContext(Dispatchers.IO) {
            println("BoardService.findByMemberId 내부 : ${Thread.currentThread().name}")
            val boards = boardRepository.findByMemberId(memberId)
            delay(4000)

            boards.map {
                BoardDto(
                    id = it.id !!,
                    memberId = it.memberId,
                    title = it.title,
                    content = it.content,
                )
            }

        }

    }

}