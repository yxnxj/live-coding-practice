package tennis

interface TennisGame {
    fun getScore(): String
    fun wonPoint(playerName: String)
}
