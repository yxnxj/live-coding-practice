package theatricalplayers.playtypes

import theatricalplayers.Performance
import kotlin.math.max

interface PlayTypeInterface {
    val defaultAmount: Int

    // 청구서 계산
    fun calculateAmount(performance: Performance): Int
    // 포인트 계산
    fun calculateVolumeCredits(performance: Performance): Int {
        return max(performance.audience - 30, 0)
    }
}