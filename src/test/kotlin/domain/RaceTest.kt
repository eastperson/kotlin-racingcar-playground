package domain

import domain.movingstrategy.DefaultMovingStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RaceTest {

    private lateinit var participants: Participants
    private lateinit var epCar: Car
    private lateinit var jakeCar: Car

    @BeforeEach
    internal fun setUp() {
        epCar = Car(name = CarName("ep"), position = Position())
        jakeCar = Car(name = CarName("jake"), position = Position())
        participants = Participants(
            listOf(
                epCar,
                jakeCar
            )
        )
    }

    @Test
    fun `매 라운드를 진행시키면 차는 무빙 전략을 실행한다`() {
        val kim = Car(name = CarName("kim"), position = Position())
        participants.participate(kim)
        val race = Race(participants, Position(9))
        race.changeMovingStrategy(DefaultMovingStrategy())
        race.nextRound()
        assertThat(kim.position).isEqualTo(Position(1))
    }

    @Test
    fun `finish 위치에 차가 도착하면 경기는 멈춘다`() {
        val race = Race(participants, Position((2)))
        race.nextRound()
        race.nextRound()
        assertThat(race.isEnd()).isTrue
    }

    @Test
    fun `경기가 멈췄는데 라운드를 진행하면 에러가 발생한다`() {
        val race = Race(participants, Position((2)))
        race.nextRound()
        race.nextRound()
        assertThrows<RuntimeException> { race.nextRound() }
    }

    @Test
    fun `가장 먼저 도착해 우승한 멤버를 가져온다`() {
        val race = Race(participants, Position((2)))
        race.changeMovingStrategy {
            if (it.name == CarName("ep")) {
                it.move()
            }
        }
        race.nextRound()
        race.nextRound()
        val winners = race.winners()
        assertThat(winners).containsOnly(epCar)
    }

    @Test
    fun `우승자가 여러명일 수도 있다`() {
        val race = Race(participants, Position((2)))
        race.nextRound()
        race.nextRound()
        val winners = race.winners()
        assertThat(winners).containsExactly(epCar, jakeCar)
    }
}