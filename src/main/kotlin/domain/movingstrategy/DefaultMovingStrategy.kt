package domain.movingstrategy

import domain.Car

class DefaultMovingStrategy : MovingStrategy {
    override fun move(car: Car) {
        car.move()
    }
}
