package view

import domain.Car
import domain.Participants
import domain.Position
import domain.Race
import domain.movingstrategy.RandomNumberStrategy
import java.lang.Integer.parseInt
import java.util.Scanner
import java.util.StringJoiner

class ResultView {

    fun start() {
        val scanner = Scanner(System.`in`)
        println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).")
        val carNames = scanner.nextLine()
        val cars = StringToCar(carNames).toCars()
        println("시도할 회수는 몇회인가요?")
        val finalRound = parseInt(scanner.nextLine())
        val race = Race(Participants(cars), Position(finalRound))
        race.changeMovingStrategy(RandomNumberStrategy())
        while (race.processing()) {
            val currentBoard = race.currentBoard()
            currentBoard.currentCarData.forEach {
                val process = currentProcess(it.position)
                println("${it.carName} : $process")
            }
            println()
            Thread.sleep(1000)
            race.nextRound()
        }
        val winners = race.winners()
        val winnerNames = getWinnersName(winners)
        println("$winnerNames 가 최종 우승했습니다.")
    }

    private fun getWinnersName(winners: List<Car>): Any {
        val stringJoiner = StringJoiner(",")
        winners.forEach {
            stringJoiner.add(it.name.name)
        }
        return stringJoiner.toString()
    }

    private fun currentProcess(position: Int): String {
        var process = ""
        for (i: Int in 1..position) {
            process += ">"
        }
        return process
    }
}