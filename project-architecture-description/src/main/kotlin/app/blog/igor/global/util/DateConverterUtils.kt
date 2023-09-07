package app.blog.igor.global.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun LocalDate?.toStartOfDay(): LocalDateTime? {
    return this?.atTime(LocalTime.MIN)
}

fun LocalDate?.toEndOfDay(): LocalDateTime? {
    return this?.atTime(23, 59, 59)
}