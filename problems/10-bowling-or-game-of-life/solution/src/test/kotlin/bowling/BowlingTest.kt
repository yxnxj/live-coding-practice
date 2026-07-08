package bowling

fun main() {
    BowlingTest().scoresGutterGame()
    println("All tests passed")
}

class BowlingTest {
    fun scoresGutterGame() {
        val game = BowlingGame()
        repeat(20) {
            game.roll(0)
        }

        assertEquals(0, game.score())
    }
}

fun assertEquals(expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("Expected <$expected> but got <$actual>")
    }
}
