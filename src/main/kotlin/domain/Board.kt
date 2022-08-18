package domain

class Board(
    val currentCarData: List<CurrentCarData>
)

data class CurrentCarData(
    val carName: String,
    val position: Int
)