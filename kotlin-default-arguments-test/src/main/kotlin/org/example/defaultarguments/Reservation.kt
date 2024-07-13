package org.example.defaultarguments

import java.time.LocalDateTime
import java.util.UUID

data class Reservation(
    val reservationId: String,
    val passengerName: String,
    val bookingTime: LocalDateTime
)


object ReservationFixture {
    fun reservation(
        reservationId: String = UUID.randomUUID().toString(),
        passengerName: String = "홍길동",
        bookingTime: LocalDateTime = LocalDateTime.now()
    ): Reservation = Reservation(
        reservationId = reservationId,
        passengerName = passengerName,
        bookingTime = bookingTime,
    )
}

//private Reservation reservation(LocalDateTime bookingTime) {
//    return Reservation.builder()
//        .reservationId("ART4320219252302")
//        .passengerName("홍길동")
//        .bookingTime(bookingTime)
//        .build();
//}