package args

fun main() {
    ArgsTest().parsesBooleanFlag()
    println("All tests passed")
}

class ArgsTest {
    fun parsesBooleanFlag() {
        assertEquals(true, ArgsParser().parseBoolean("l", listOf("-l")))
    }
}

fun assertEquals(expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("Expected <$expected> but got <$actual>")
    }
}
