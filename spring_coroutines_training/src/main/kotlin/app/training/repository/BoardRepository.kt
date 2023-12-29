package app.training.repository;

import app.training.model.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long> {

    fun findByMemberId(memberId: Long) : List<Board>

}