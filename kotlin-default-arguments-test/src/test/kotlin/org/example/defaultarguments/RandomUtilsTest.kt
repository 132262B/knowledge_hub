package org.example.defaultarguments

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

class RandomUtilsTest {

    @Test
    fun `메서드를 호출하면 랜덤으로 할인 퍼센트를 생성한다`() {
        // given
        val seed = 2
        val random = Random(seed)
        val randomNumber = Random(seed).nextInt(100)

        // when
        val discountPercent = RandomUtils.generateDiscountPercent(random)

        // then
        assertThat(discountPercent).isEqualTo(randomNumber)
    }
}