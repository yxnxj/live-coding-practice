package tennis

fun main() {
    val test = TennisTest("p1", "p2")
    test.checkAllScoresTennisGame1()
    test.checkAllScoresTennisGame2()
//    test.checkAllScoresTennisGame3()
//    test.checkAllScoresTennisGame4()
//    test.checkAllScoresTennisGame5()
//    test.checkAllScoresTennisGame6()
//    test.checkAllScoresTennisGame7()
    println("All tests passed")
}

class TennisTest(private val player1Name: String, private val player2Name: String) {
    fun checkAllScoresTennisGame1() {
        checkAllScores(createGame = { TennisGame1(player1Name, player2Name) })
    }

    fun checkAllScoresTennisGame2() {
        checkAllScores(createGame = { TennisGame2(player1Name, player2Name) })
    }

//    fun checkAllScoresTennisGame3() {
//        checkAllScores(createGame = { TennisGame3(player1Name, player2Name) })
//    }
//
//    fun checkAllScoresTennisGame4() {
//        checkAllScores(createGame = { TennisGame4(player1Name, player2Name) })
//    }
//
//    fun checkAllScoresTennisGame5() {
//        checkAllScores(createGame = { TennisGame5(player1Name, player2Name) })
//    }
//
//    fun checkAllScoresTennisGame6() {
//        checkAllScores(createGame = { TennisGame6(player1Name, player2Name) })
//    }
//
//    fun checkAllScoresTennisGame7() {
//        checkAllScores(
//            createGame = { TennisGame7(player1Name, player2Name) },
//            expectedScore = { "Current score: $it, enjoy your game!" },
//        )
//    }

    private fun checkAllScores(
        createGame: () -> TennisGame,
        expectedScore: (String) -> String = { it },
    ) {
        allScores().forEach { (player1Score, player2Score, expected) ->
            val game = createGame()
            playPoints(game, player1Score, player2Score)
            assertEquals(expectedScore(expected), game.getScore())
        }
    }

    private fun playPoints(game: TennisGame, player1Score: Int, player2Score: Int) {
        val highestScore = maxOf(player1Score, player2Score)
        for (i in 0 until highestScore) {
            if (i < player1Score)
                game.wonPoint(player1Name)
            if (i < player2Score)
                game.wonPoint(player2Name)
        }
    }

    private fun allScores(): List<ScoreCase> =
        listOf(
            ScoreCase(0, 0, "Love-All"),
            ScoreCase(1, 1, "Fifteen-All"),
            ScoreCase(2, 2, "Thirty-All"),
            ScoreCase(3, 3, "Deuce"),
            ScoreCase(4, 4, "Deuce"),
            ScoreCase(1, 0, "Fifteen-Love"),
            ScoreCase(0, 1, "Love-Fifteen"),
            ScoreCase(2, 0, "Thirty-Love"),
            ScoreCase(0, 2, "Love-Thirty"),
            ScoreCase(3, 0, "Forty-Love"),
            ScoreCase(0, 3, "Love-Forty"),
            ScoreCase(4, 0, "Win for $player1Name"),
            ScoreCase(0, 4, "Win for $player2Name"),
            ScoreCase(2, 1, "Thirty-Fifteen"),
            ScoreCase(1, 2, "Fifteen-Thirty"),
            ScoreCase(3, 1, "Forty-Fifteen"),
            ScoreCase(1, 3, "Fifteen-Forty"),
            ScoreCase(4, 1, "Win for $player1Name"),
            ScoreCase(1, 4, "Win for $player2Name"),
            ScoreCase(3, 2, "Forty-Thirty"),
            ScoreCase(2, 3, "Thirty-Forty"),
            ScoreCase(4, 2, "Win for $player1Name"),
            ScoreCase(2, 4, "Win for $player2Name"),
            ScoreCase(4, 3, "Advantage $player1Name"),
            ScoreCase(3, 4, "Advantage $player2Name"),
            ScoreCase(5, 4, "Advantage $player1Name"),
            ScoreCase(4, 5, "Advantage $player2Name"),
            ScoreCase(15, 14, "Advantage $player1Name"),
            ScoreCase(14, 15, "Advantage $player2Name"),
            ScoreCase(6, 4, "Win for $player1Name"),
            ScoreCase(4, 6, "Win for $player2Name"),
            ScoreCase(16, 14, "Win for $player1Name"),
            ScoreCase(14, 16, "Win for $player2Name"),
        )
}

data class ScoreCase(
    val player1Score: Int,
    val player2Score: Int,
    val expectedScore: String,
)

fun assertEquals(expected: Any?, actual: Any?) {
    if (expected != actual) {
        throw AssertionError("Expected <$expected> but got <$actual>")
    }
}
