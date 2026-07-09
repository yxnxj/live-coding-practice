package tennis

fun main() {
    TennisTest().startsAtLoveAll()
    println("All tests passed")
}

class TennisTest {
    fun startsAtLoveAll() {
        assertEquals("Love-All", TennisGame().score(0, 0))
    }
}

fun assertEquals(expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("Expected <$expected> but got <$actual>")
    }
}
