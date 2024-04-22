package delegate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PersonTest4 {
    @Test
    fun isKimTest() {
        // given
        val person = Person4()

        // when & then
        assertThat(person.isKim).isTrue()
    }
}
