package app.training.dto

data class BoardDto(
    val id: Long,
    val memberId : Long,
    val title: String,
    val content: String,
)
