package theatricalplayers

data class StatementInfo(
    val totalAmount: Int,
    val volumeCredits: Int,
    val lines: List<StatementLine>,
)

data class StatementLine(
    val playName: String,
    val amount: Int,
    val audience: Int,
)
