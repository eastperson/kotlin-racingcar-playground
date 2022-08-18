package domain

class Position(
    var position: Int = 0
) : Comparable<Position> {
    override fun compareTo(other: Position) = this.position - other.position

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Position) return false

        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        return position
    }

    fun moveForward() {
        this.position++
    }
}
