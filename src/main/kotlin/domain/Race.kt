package domain

import domain.movingstrategy.DefaultMovingStrategy
import domain.movingstrategy.MovingStrategy

class Race(
    private val participants: Participants,
    private val finishPosition: Position
) {

    private var movingStrategy: MovingStrategy = DefaultMovingStrategy()
    private var end: Boolean = false

    fun nextRound() {
        if (end) {
            throw RuntimeException("종료된 레이스입니다.")
        }
        this.participants.move(movingStrategy)
        val firstPosition = this.participants.currentFirstPosition()
        if (firstPosition == finishPosition) {
            end = true
        }
    }

    fun changeMovingStrategy(movingStrategy: MovingStrategy) {
        this.movingStrategy = movingStrategy
    }

    fun isEnd(): Boolean = this.end
    fun winners(): List<Car> = this.participants.winners(this.finishPosition)
    fun processing() = !this.end
    fun currentBoard(): Board {
        val dataList = mutableListOf<CurrentCarData>()
        this.participants.currentCars().map {
            dataList.add(CurrentCarData(it.name.name, it.position.position))
        }
        return Board(dataList)
    }
}
