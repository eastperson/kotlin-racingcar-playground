package domain.movingstrategy

import domain.Car

class RandomNumberStrategy : MovingStrategy {

    companion object {
        const val RANDOM_MOVE_START_VALUE = 4
    }

    override fun move(car: Car) {
        if (generateRandomNumber() >= RANDOM_MOVE_START_VALUE) {
            car.move()
        }
    }

    private fun generateRandomNumber(): Int {
        val range = 1..9
        return range.random()
    }
}
