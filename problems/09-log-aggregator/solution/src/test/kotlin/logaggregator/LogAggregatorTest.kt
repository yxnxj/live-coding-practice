package logaggregator

fun main() {
    LogAggregatorTest().countsActionsByUser()
    println("All tests passed")
}

class LogAggregatorTest {
    fun countsActionsByUser() {
        val entries = listOf(
            LogEntry("2026-07-08T10:00:00", "u1", "login"),
            LogEntry("2026-07-08T10:01:00", "u1", "click"),
        )

        assertEquals(mapOf("u1" to 2), LogAggregator().countByUser(entries))
    }
}

fun assertEquals(expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("Expected <$expected> but got <$actual>")
    }
}
