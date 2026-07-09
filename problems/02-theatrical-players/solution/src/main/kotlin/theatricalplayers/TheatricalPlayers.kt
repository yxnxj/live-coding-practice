package theatricalplayers

fun statement(invoice: Invoice, plays: Map<String, Play>): String {
    return StatementPrinter().print(invoice, plays)
}
