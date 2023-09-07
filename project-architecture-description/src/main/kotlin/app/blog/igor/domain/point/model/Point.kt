package app.blog.igor.domain.point.model

import app.blog.igor.domain.common.BaseTimeEntity
import java.math.BigDecimal
import javax.persistence.Entity

@Entity
class Point(
    memberId: Long,
    reasonEarning: String,
    reserveAmount: BigDecimal,
    eventId: Long? = null,
) : BaseTimeEntity() {

    var memberId: Long = memberId
        protected set

    var reasonEarning: String = reasonEarning
        protected set

    var reserveAmount: BigDecimal = reserveAmount
        protected set

    var eventId: Long? = eventId
        protected set
}