package domain

class CarName(
    val name: String
) {
    init {
        validate(name)
    }

    private fun validate(name: String) {
        check(name.isNotEmpty()) { "자동차의 이름은 1자 이상이어야 합니다" }
        check(name.length < 6) { "자동차의 이름은 5자를 넘으면 안됩니다" }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CarName) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
