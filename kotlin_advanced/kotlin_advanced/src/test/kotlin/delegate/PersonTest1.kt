package delegate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class PersonTest1 {

    @Test
    fun isKimTest() {
        // given
        val person = Person("김수한무거북이와두루미")
        // when & then
        assert(person.isKim)
    }

    @Test
    fun getMaskingName() {
        // given
        val person = Person("권준호")
        // when & then
        assertThat(person.maskingName).isEqualTo("권**")
    }
}