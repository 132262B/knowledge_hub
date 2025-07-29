package app.demo.coupon

import app.demo.redisson.DistributedLock
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CouponService(
    private val couponRepository: CouponRepository,
) {

    @DistributedLock(key = "'issueCoupon_' + #couponId")
    fun issuance(couponId: Long): Int {
        val coupon = couponRepository
            .findByIdOrNull(couponId) ?: throw RuntimeException("coupon not found")

        coupon.issuance()
        return coupon.quantity
    }
}