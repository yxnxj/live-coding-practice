package theatricalplayers

data class Performance(val playId: String, val audience: Int)
data class Invoice(val customer: String, val performances: List<Performance>)
data class Play(val name: String, val type: String)

fun statement(invoice: Invoice, plays: Map<String, Play>): String {
    return "Statement for ${invoice.customer}\nAmount owed is $0.00\nYou earned 0 credits\n"
}
