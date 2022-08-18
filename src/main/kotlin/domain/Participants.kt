package domain

import domain.movingstrategy.MovingStrategy

class Participants(
    private var cars: List<Car>
) {
    fun participate(car: Car) {
        val mutableParticipants = this.cars.toMutableList()
        mutableParticipants.add(car)
        this.cars = mutableParticipants.toList()
    }

    fun currentCars() = this.cars

    fun count() = this.cars.size
    fun isParticipant(car: Car) = this.cars.contains(car)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Participants) return false

        if (cars != other.cars) return false

        return true
    }

    override fun hashCode(): Int {
        return cars.hashCode()
    }

    fun move(movingStrategy: MovingStrategy) {
        this.cars.forEach {
            it.move(movingStrategy)
        }
    }

    fun currentFirstPosition(): Position {
        return this.cars.toMutableList().sortedByDescending { it.position }.first().position
    }

    fun winners(finalPosition: Position): List<Car> = this.cars.toMutableList().filter { it.position == finalPosition }.toList()
}
