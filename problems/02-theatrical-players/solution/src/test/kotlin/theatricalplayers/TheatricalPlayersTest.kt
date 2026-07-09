package theatricalplayers

fun main() {
    TheatricalPlayersTest().printsStatementHeader()
    println("All tests passed")
}

class TheatricalPlayersTest {
    fun printsStatementHeader() {
        val invoice = Invoice("BigCo", emptyList())
        val actual = statement(invoice, emptyMap())

        assertEquals(
            "Statement for BigCo\nAmount owed is $0.00\nYou earned 0 credits\n",
            actual,
        )
    }
}

fun assertEquals(expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("Expected <$expected> but got <$actual>")
    }
}
