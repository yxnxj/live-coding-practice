package bowling

class BowlingGame {
    private val rolls = mutableListOf<Int>()

    fun roll(pins: Int) {
        rolls.add(pins)
    }

    fun score(): Int {
        return rolls.sum()
    }
}
