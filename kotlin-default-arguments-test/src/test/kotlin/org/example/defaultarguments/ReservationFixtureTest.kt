package org.example.defaultarguments

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ReservationFixtureTest {

    @Test
    fun `테스트 1`() {
        val date = LocalDateTime.of(2024, 9, 25, 23, 2)
        val reservation = ReservationFixture.reservation(
            bookingTime = date
        )

        // 생략
    }

    @Test
    fun `테스트 2`() {
        val reservationId = "ARB00000000001"
        val reservation = ReservationFixture.reservation(
            reservationId = reservationId
        )

        // 생략
    }

}