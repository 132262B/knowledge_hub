package app.demo.coupon

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors


@SpringBootTest
class CouponIssuanceConcurrencyTest {

    private val numberOfThreads = 100
    private val executorService = Executors.newFixedThreadPool(numberOfThreads)

    @Autowired
    private lateinit var couponDecreaseService: CouponService

    @Autowired
    private lateinit var couponRepository: CouponRepository

    private lateinit var coupon: Coupon

    @BeforeEach
    fun setUp() {
        coupon = Coupon("쿠폰", 100)
        couponRepository.save(coupon)
    }

    @Test
    fun `쿠폰차감 분산락 적용 동시성 100명 테스트`() {
        val lockName: String = "coupon"
        val latch = CountDownLatch(numberOfThreads)

        repeat(numberOfThreads) {
            executorService.submit {
                try {
                    couponDecreaseService.issuance(lockName, coupon.id)
                } finally {
                    latch.countDown()
                }
            }
        }

        latch.await()

        val persistCoupon = couponRepository.findById(coupon.id)
            .orElseThrow { IllegalArgumentException("쿠폰 없음") }

        assertThat(persistCoupon.quantity).isZero()
        println("잔여 쿠폰 개수 = ${persistCoupon.quantity}")
    }


    @Test
    fun `쿠폰차감 분산락 미적용 동시성 100명 테스트`() {
        val latch = CountDownLatch(numberOfThreads)

        repeat(numberOfThreads) {
            executorService.submit {
                try {
                    couponDecreaseService.issuance(coupon.id)
                } finally {
                    latch.countDown()
                }
            }
        }

        latch.await()

        val persistCoupon = couponRepository.findById(coupon.id)
            .orElseThrow { IllegalArgumentException("쿠폰 없음") }

        assertThat(persistCoupon.quantity).isZero()
        println("잔여 쿠폰 개수 = ${persistCoupon.quantity}")
    }

}