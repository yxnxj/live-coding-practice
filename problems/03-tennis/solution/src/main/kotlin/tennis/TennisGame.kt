package tennis

class TennisGame {
    fun score(player1Points: Int, player2Points: Int): String {
        return when {
            player1Points == 0 && player2Points == 0 -> "Love-All"
            else -> "TODO"
        }
    }
}
