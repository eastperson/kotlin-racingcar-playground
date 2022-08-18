package domain

import domain.movingstrategy.DefaultMovingStrategy
import domain.movingstrategy.MovingStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * 기능 요구사항
 * - 각 자동차에 이름을 부여할 수 있다.
 *  - 자동차의 이름을 1~5자 이다.
 * - 각 자동차는 위치를 가지고 있다.
 * - 자동차는 앞으로 전진할 수 있다.
 *  - 자동차는 앞으로 1칸씩 전진할 수 있다.
 *  - 자동차가 갈 수 있는 거리는 제한되어 있다.
 *  - 자동차가 전진하는 조건은 Random 조건으로 만들어진다.
 *      - Random 조건은 0~9 사이의 값 중 4이상일 때이다.
 *  - 경주 게임이 끝나면 누가 우승했는지 알려준다.
 */
class CarTest {

    private lateinit var car: Car

    @BeforeEach
    internal fun setUp() {
        car = Car(name = CarName("ep"), position = Position())
    }

    @Test
    fun `자동차는_이름과_위치를_가질_수_있다`() {
        assertThat(car).isEqualTo(Car(name = CarName("ep"), position = Position()))
    }

    @Test
    fun `자동차는_위치를_움직일 수 있다`() {
        car.move()
        assertThat(car.position).isEqualTo(Position(1))
    }

    @Test
    fun `자동차는_moving_전략으로_sam_object_인자를_받을_수_있다`() {
        car.move(
            object : MovingStrategy {
                override fun move(car: Car) {
                    car.move()
                    car.move()
                }
            }
        )
        assertThat(car.position).isEqualTo(Position(2))
    }

    @Test
    fun `자동차는_moving_전략으로_strategy_구현체를_인자를_받을_수_있다`() {
        car.move(DefaultMovingStrategy())
        assertThat(car.position).isEqualTo(Position(1))
    }

    @Test
    fun `자동차는_moving_전략으로_lambda_인자를_받을_수_있다`() {
        car.move {
            it.move()
            it.move()
            it.move()
        }
        assertThat(car.position).isEqualTo(Position(3))
    }

    @Test
    fun `자동차는_moving_전략으로_SAM_conversion_인자를_받을_수_있다`() {
        val doubleMovingStrategy = MovingStrategy {
            it.move()
            it.move()
        }
        car.move(doubleMovingStrategy)
        assertThat(car.position).isEqualTo(Position(2))
    }

    @Test
    fun `자동차는_누가_앞에_있는지_확인할_수_있다`() {
        val other = Car(CarName("other"), Position())

        car.move()
        car.move()
        other.move()

        assertThat(car.isWin(other)).isTrue
    }
}
