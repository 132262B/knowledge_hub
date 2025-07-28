package app.demo.coupon

import app.demo.redisson.DistributedLock
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CouponService(
    private val couponRepository: CouponRepository,
) {

    @DistributedLock(key = "'issueCoupon_' + #couponId")
    fun issueCoupon(couponId: Long): Int {
        val coupon = couponRepository
            .findByIdOrNull(couponId) ?: throw RuntimeException("coupon not found")

        coupon.issueCoupon()

        if(coupon.quantity <= -1) {
            throw RuntimeException("coupon quantity is greater than zero")
        }

        return coupon.quantity
    }
}