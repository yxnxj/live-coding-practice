package theatricalplayers

import java.text.NumberFormat
import java.util.Locale
import kotlin.math.floor
import kotlin.math.max

class StatementPrinter {

    fun print(invoice: Invoice, plays: Map<String, Play>): String {
        var totalAmount = 0
        var volumeCredits = 0
        var result = "Statement for ${invoice.customer}\n"

        val format = { number: Long -> NumberFormat.getCurrencyInstance(Locale.US).format(number) }

        invoice.performances.forEach { performance ->
            val play = plays.getValue(performance.playId)
            var thisAmount = 0

            when (play.type) {
                "tragedy" -> {
                    thisAmount = 40000
                    if (performance.audience > 30) {
                        thisAmount += 1000 * (performance.audience - 30)
                    }
                }
                "comedy" -> {
                    thisAmount = 30000
                    if (performance.audience > 20) {
                        thisAmount += 10000 + 500 * (performance.audience - 20)
                    }
                    thisAmount += 300 * performance.audience
                }
                else -> throw Error("unknown type: ${play.type}")
            }

            volumeCredits += max(performance.audience - 30, 0)
            if ("comedy" == play.type) {
                volumeCredits += floor((performance.audience / 5).toDouble()).toInt()
            }

            result += "  ${play.name}: ${format((thisAmount / 100).toLong())} (${performance.audience} seats)\n"
            totalAmount += thisAmount
        }

        result += "Amount owed is ${format((totalAmount / 100).toLong())}\n"
        result += "You earned $volumeCredits credits\n"
        return result
    }
}
