package theatricalplayers.playtypes

import theatricalplayers.Performance

object PlayTypeTragedy: PlayTypeInterface {
    override val defaultAmount: Int = 40000

    override fun calculateAmount(performance: Performance): Int {
        var thisAmount = defaultAmount
        if (performance.audience > 30) {
            thisAmount += 1000 * (performance.audience - 30)
        }

        return thisAmount
    }
}