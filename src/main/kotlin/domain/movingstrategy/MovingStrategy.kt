package domain.movingstrategy

import domain.Car

/**
 * Kotlin Functional Interface
 * 익명객체(object), 구현체, Kotlin's SAM conversion, 람다 사용가능
 */
fun interface MovingStrategy {
    fun move(car: Car)
}
