package domain

import domain.movingstrategy.MovingStrategy

class Car(
    val name: CarName,
    val position: Position
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Car) return false

        if (name != other.name) return false
        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + position.hashCode()
        return result
    }

    fun move() {
        this.position.moveForward()
    }

    fun move(strategy: MovingStrategy) {
        strategy.move(this)
    }

    fun isWin(other: Car) = this.position > other.position
}
