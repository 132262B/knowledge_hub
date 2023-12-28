package app.training.dto

data class CommentDto(
    val id: Long,
    val memberId: Long,
    val boardId: Long,
    val content: String,
)
