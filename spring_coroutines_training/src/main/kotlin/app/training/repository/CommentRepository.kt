package app.training.repository;

import app.training.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findByMemberId(memberId: Long) : Comment

}