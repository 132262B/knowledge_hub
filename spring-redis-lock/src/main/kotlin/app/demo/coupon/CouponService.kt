package app.demo.coupon

import app.demo.redisson.DistributedLock
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponService(
    private val couponRepository: CouponRepository,
) {

    @Transactional
    fun issuance(
        couponId: Long
    ): Int {
        val coupon = couponRepository
            .findByIdOrNull(couponId) ?: throw RuntimeException("coupon not found")

        coupon.issuance()
        return coupon.quantity
    }

    @DistributedLock(key = "#lockName+#couponId")
    fun issuance(
        lockName : String,
        couponId: Long
    ): Int {
        val coupon = couponRepository
            .findByIdOrNull(couponId) ?: throw RuntimeException("coupon not found")

        coupon.issuance()
        return coupon.quantity
    }
}