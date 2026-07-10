package theatricalplayers

import theatricalplayers.playtypes.PlayTypeComedy
import theatricalplayers.playtypes.PlayTypeInterface
import theatricalplayers.playtypes.PlayTypeTragedy
import java.text.NumberFormat
import java.util.Locale

class StatementPrinter {
    companion object{
        val calculateHandler: Map<String, PlayTypeInterface> = mapOf(
            "tragedy" to PlayTypeTragedy,
            "comedy" to PlayTypeComedy
        )
    }

    private val format = { number: Long -> NumberFormat.getCurrencyInstance(Locale.US).format(number) }

    fun print(invoice: Invoice, plays: Map<String, Play>): String {
        var result = "Statement for ${invoice.customer}\n"
        val statementInfo = createStatementInfo(invoice, plays)

        result += statementInfo.lines.joinToString("") {
            "  ${it.playName}: ${format((it.amount / 100).toLong())} (${it.audience} seats)\n"
        }

        result += "Amount owed is ${format((statementInfo.totalAmount / 100).toLong())}\n"
        result += "You earned ${statementInfo.volumeCredits} credits\n"
        return result
    }

    fun createStatementInfo(invoice: Invoice, plays: Map<String, Play>): StatementInfo{
        var totalAmount = 0
        var volumeCredits = 0
        val statementLines = mutableListOf<StatementLine>()

        invoice.performances.forEach { performance ->
            val play = plays.getValue(performance.playId)
            var thisAmount = 0

            calculateHandler[play.type]?.let { handler ->
                thisAmount = handler.calculateAmount(performance)
                volumeCredits += handler.calculateVolumeCredits(performance)
            } ?: throw Error("unknown type: ${play.type}")

            statementLines.add(StatementLine(playName = play.name, amount = thisAmount, audience = performance.audience))
            totalAmount += thisAmount
        }

        return StatementInfo(
            totalAmount = totalAmount,
            volumeCredits = volumeCredits,
            lines = statementLines
        )
    }

}
