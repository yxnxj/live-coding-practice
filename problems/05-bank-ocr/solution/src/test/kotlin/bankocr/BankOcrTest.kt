package bankocr

fun main() {
    BankOcrTest().parsesZero()
    println("All tests passed")
}

class BankOcrTest {
    fun parsesZero() {
        assertEquals("0", BankOcr().parseDigit(listOf(" _ ", "| |", "|_|")))
    }
}

fun assertEquals(expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("Expected <$expected> but got <$actual>")
    }
}
