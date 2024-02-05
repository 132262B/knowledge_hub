package app.learning.enumerated

enum class OrderStatus(
    val textKr : String,
    val textEn : String,
) {

    SUBMITTED("제출", "submitted"),
    PAID("결제 완료", "paid"),
    CANCEL("취소", "cancel"),
    ;


    companion object {
        fun quantityModifiable() = SUBMITTED

    }
}