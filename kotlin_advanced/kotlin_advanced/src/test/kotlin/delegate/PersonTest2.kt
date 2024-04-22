package delegate

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class PersonTest2 {
    @Test
    fun lateinitTest() {
        // given
        val person = Person2()

        // when & then
        assertThatThrownBy { person.isKim }
            .isInstanceOf(UninitializedPropertyAccessException::class.java)
    }
}
