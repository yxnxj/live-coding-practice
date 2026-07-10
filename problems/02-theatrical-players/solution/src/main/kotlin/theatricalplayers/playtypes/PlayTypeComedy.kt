package theatricalplayers.playtypes

import theatricalplayers.Performance
import kotlin.math.floor
import kotlin.math.max

object PlayTypeComedy: PlayTypeInterface {
    override val defaultAmount: Int = 30000

    override fun calculateAmount(performance: Performance): Int {
        var thisAmount = defaultAmount
        if (performance.audience > 20) {
            thisAmount += 10000 + 500 * (performance.audience - 20)
        }
        thisAmount += 300 * performance.audience

        return thisAmount
    }

    override fun calculateVolumeCredits(performance: Performance): Int {
        return max(performance.audience - 30, 0) + floor((performance.audience / 5).toDouble()).toInt()
    }
}