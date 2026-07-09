package theatricalplayers

fun main() {
    TheatricalPlayersTest().printsExampleStatement()
    TheatricalPlayersTest().throwsForUnknownPlayTypes()
    println("All tests passed")
}

class TheatricalPlayersTest {
    fun printsExampleStatement() {
        val actual = statement(exampleInvoice(), existingPlays())

        assertEquals(
            """
            Statement for BigCo
              Hamlet: ${'$'}650.00 (55 seats)
              As You Like It: ${'$'}580.00 (35 seats)
              Othello: ${'$'}500.00 (40 seats)
            Amount owed is ${'$'}1,730.00
            You earned 47 credits

            """.trimIndent(),
            actual,
        )
    }

    fun throwsForUnknownPlayTypes() {
        assertThrows<Error> {
            statement(invoiceWithNewPlayTypes(), newPlays())
        }
    }
}

fun existingPlays(): Map<String, Play> {
    return mapOf(
        "hamlet" to Play("Hamlet", "tragedy"),
        "as-like" to Play("As You Like It", "comedy"),
        "othello" to Play("Othello", "tragedy"),
    )
}

fun exampleInvoice(): Invoice {
    return Invoice(
        "BigCo",
        listOf(
            Performance("hamlet", 55),
            Performance("as-like", 35),
            Performance("othello", 40),
        ),
    )
}

fun newPlays(): Map<String, Play> {
    return mapOf(
        "henry-v" to Play("Henry V", "history"),
        "as-like" to Play("As You Like It", "pastoral"),
    )
}

fun invoiceWithNewPlayTypes(): Invoice {
    return Invoice(
        "BigCo",
        listOf(
            Performance("henry-v", 53),
            Performance("as-like", 55),
        ),
    )
}

fun assertEquals(expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("Expected <$expected> but got <$actual>")
    }
}

inline fun <reified T : Throwable> assertThrows(block: () -> Unit) {
    try {
        block()
    } catch (actual: Throwable) {
        if (actual is T) {
            return
        }
        throw AssertionError("Expected ${T::class.qualifiedName} but got $actual", actual)
    }

    throw AssertionError("Expected ${T::class.qualifiedName} but nothing was thrown")
}
