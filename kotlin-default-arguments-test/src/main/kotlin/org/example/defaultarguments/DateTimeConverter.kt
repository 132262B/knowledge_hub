package org.example.defaultarguments

import java.time.LocalDateTime

object DateTimeConverter {


    fun createPaymentExpirationDate(
        currentDate : LocalDateTime = LocalDateTime.now(),
    ) : LocalDateTime =
        currentDate.plusDays(7)

    fun createOrder(/* 생략 */){
        // .. 생략
        val expirationDate = createPaymentExpirationDate()
        // .. 생략
    }


    fun currentFormattedDateTime(currentTime: LocalDateTime): FormattedDateTime = FormattedDateTime(
        year = currentTime.year,
        month = currentTime.monthValue,
        day = currentTime.dayOfMonth,
        hour = currentTime.hour,
        minute = currentTime.minute,
        second = currentTime.second,
    )


    data class FormattedDateTime(
        val year: Int,
        val month: Int,
        val day: Int,
        val hour: Int,
        val minute: Int,
        val second: Int,
    )
}