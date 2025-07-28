package app.demo.coupon

import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CouponController(
    private val couponService : CouponService,
) {

    @PatchMapping("/api/v1/coupons/{couponId}/issue")
    fun issueCoupon(@PathVariable couponId: Long): Int = couponService.issueCoupon(couponId)

}