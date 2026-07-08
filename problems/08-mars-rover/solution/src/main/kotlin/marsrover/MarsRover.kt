package marsrover

data class Position(val x: Int, val y: Int, val direction: Char)

class MarsRover(private var position: Position) {
    fun execute(commands: String): Position {
        return position
    }
}
