package view

import domain.Car
import domain.CarName
import domain.Position

class StringToCar(
    private val carNames: String
) {

    private val cars: List<Car>

    init {
        this.cars = parse(carNames)
    }

    private fun parse(carNames: String): List<Car> =
        carNames.split(",")
            .map { Car(CarName(it), Position()) }
            .toList()

    public fun toCars() = this.cars
}
