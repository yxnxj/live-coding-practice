package tennis

class TennisGame2(private val player1Name: String, private val player2Name: String) : TennisGame {
    var p1Point: Int = 0
    var p2Point: Int = 0

    companion object{
        private const val SCORE_LOVE = 0
        private const val SCORE_FIFTEEN = 1
        private const val SCORE_THIRTY = 2
        private const val SCORE_FORTY = 3
    }



    override fun getScore(): String {
        return if (isTie()) {
            scoreTie()
        } else if (isEndGame()) {
            scoreEndGame()
        } else {
            "${scoreForPoint(p1Point)}-${scoreForPoint(p2Point)}"
        }
    }

    fun isEndGame() = p1Point >= 4 || p2Point >= 4

    fun scoreEndGame(): String {
        return when(p1Point - p2Point) {
            1 -> "Advantage $player1Name"
            -1 -> "Advantage $player2Name"
            in 2..Int.MAX_VALUE -> "Win for $player1Name"
            in Int.MIN_VALUE..-2 -> "Win for $player2Name"
            else -> throw IllegalStateException("Invalid end game score state: $p1Point, $p2Point")
        }
    }

    fun isTie() = p1Point == p2Point

    fun scoreTie(): String {
        return when (p1Point) {
            SCORE_LOVE -> "Love-All"
            SCORE_FIFTEEN -> "Fifteen-All"
            SCORE_THIRTY -> "Thirty-All"
            else -> "Deuce"
        }
    }

    fun scoreForPoint(point: Int): String {
        return when (point) {
            SCORE_LOVE -> "Love"
            SCORE_FIFTEEN -> "Fifteen"
            SCORE_THIRTY -> "Thirty"
            SCORE_FORTY -> "Forty"
            else -> throw IllegalArgumentException("Invalid point: $point")
        }
    }


    override fun wonPoint(playerName: String) {
        when (playerName) {
            player1Name -> p1Point += 1
            player2Name -> p2Point += 1
            else -> throw IllegalArgumentException("Invalid player name: $playerName")
        }
    }
}
