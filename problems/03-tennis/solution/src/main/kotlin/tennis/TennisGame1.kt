package tennis

class TennisGame1(private val player1Name: String, private val player2Name: String) : TennisGame {

    private var player1Score: Int = 0
    private var player2Score: Int = 0

    companion object{
        private const val SCORE_LOVE = 0
        private const val SCORE_FIFTEEN = 1
        private const val SCORE_THIRTY = 2
        private const val SCORE_FORTY = 3

    }

    fun isTie(): Boolean = player1Score == player2Score

    fun handleTieScore(): String{
        return when (player1Score) {
            SCORE_LOVE -> "Love-All"
            SCORE_FIFTEEN -> "Fifteen-All"
            SCORE_THIRTY -> "Thirty-All"
            else -> "Deuce"
        }
    }

    fun isEndGame(): Boolean = player1Score >= 4 || player2Score >= 4

    fun handleEndGameScore(): String{
        return when (player1Score-player2Score){
            1 -> "Advantage player1"
            -1 -> "Advantage player2"
            in 2..Int.MAX_VALUE -> "Win for player1"
            in Int.MIN_VALUE..-2 -> "Win for player2"
            else -> throw IllegalStateException("Invalid end game score state: $player1Score-$player2Score")
        }
    }

    fun getScore(scoreNum: Int): String{
        return when(scoreNum) {
            SCORE_LOVE -> "Love"
            SCORE_FIFTEEN -> "Fifteen"
            SCORE_THIRTY -> "Thirty"
            SCORE_FORTY -> "Forty"
            else -> throw IllegalArgumentException("Invalid score: $scoreNum")
        }
    }

    override fun wonPoint(playerName: String) {
        when (playerName) {
            "player1" -> player1Score += 1
            "player2" -> player2Score += 1
            else -> throw IllegalArgumentException("Invalid player name: $playerName")
        }
    }

    override fun getScore(): String {
        if (isTie()) {
            return handleTieScore()
        } else if (isEndGame()) {
            return handleEndGameScore()
        }
        return listOf(getScore(player1Score), getScore(player2Score)).joinToString("-")
    }

}
