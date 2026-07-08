package bankocr

class BankOcr {
    fun parseDigit(lines: List<String>): String {
        return if (lines == listOf(" _ ", "| |", "|_|")) "0" else "?"
    }
}
