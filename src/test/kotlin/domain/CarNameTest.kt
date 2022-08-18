package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CarNameTest {

    @Test
    fun `자동차이름에_공백이_올_수_없습니다`() {
        assertThrows<IllegalStateException> { CarName("") }
    }

    @Test
    fun `자동차이름은_5자를_넘길_수_없습니다`() {
        assertThrows<IllegalStateException> { CarName("eastperson") }
    }

    @Test
    fun `자동차_이름은_문자열을_인자로_갖습니다`() {
        val carName = CarName("ep")
        assertThat(carName).isEqualTo(CarName("ep"))
    }
}
