package org.example.defaultarguments

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DateTimeConverterTest {

    @Test
    fun `메서드를 호출하면 현재 시간 기준으로 결제 만료일을 반환한다`() {
        // given
        val currentTime = LocalDateTime.of(2021, 1, 1, 0, 0, 0)
        val expectedExpirationDate = LocalDateTime.of(2021, 1, 8, 0, 0, 0)

        // when
        val result = DateTimeConverter.createPaymentExpirationDate(currentTime)

        // then
        assertThat(expectedExpirationDate).isEqualTo(result)
    }

}