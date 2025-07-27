package app.demo.coupon

import org.springframework.data.jpa.repository.JpaRepository


interface CouponRepository : JpaRepository<Coupon, Long>