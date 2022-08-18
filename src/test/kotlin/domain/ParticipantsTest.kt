package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantsTest {

    private lateinit var participants: Participants

    @BeforeEach
    internal fun setUp() {
        participants = Participants(
            listOf(
                Car(name = CarName("ep"), position = Position()),
                Car(name = CarName("jake"), position = Position())
            )
        )
    }

    @Test
    fun `참여자는 자동차 리스트를 가지고 있다`() {
        assertThat(participants.count()).isEqualTo(2)
    }

    @Test
    fun `참여자는_본인이_참가자인지_확인할_수_있다`() {
        assertThat(participants.isParticipant(Car(name = CarName("ep"), position = Position()))).isTrue()
    }
}
