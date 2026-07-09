package marsrover

fun main() {
    MarsRoverTest().keepsInitialPositionWithoutCommands()
    println("All tests passed")
}

class MarsRoverTest {
    fun keepsInitialPositionWithoutCommands() {
        val initial = Position(0, 0, 'N')

        assertEquals(initial, MarsRover(initial).execute(""))
    }
}

fun assertEquals(expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("Expected <$expected> but got <$actual>")
    }
}
