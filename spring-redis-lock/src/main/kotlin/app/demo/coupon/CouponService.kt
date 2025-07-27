package app.demo.coupon

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponService(
    private val couponRepository: CouponRepository,
) {

    @Transactional
    fun issueCoupon(couponId: Long): Int {
        val coupon = couponRepository
            .findByIdOrNull(couponId) ?: throw RuntimeException("coupon not found")

        coupon.issueCoupon()
        return coupon.quantity
    }
}