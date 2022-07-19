import programmers.highscorekit.Carpet
import programmers.highscorekit.Immigration
import programmers.highscorekit.ShortestWayInGameMap

fun main(args: Array<String>) {

    val maps = arrayOf(intArrayOf(1,0,1,1,1),intArrayOf(1,0,1,0,1),intArrayOf(1,0,1,1,1),intArrayOf(1,1,1,0,1),intArrayOf(0,0,0,0,1))

    val problem = ShortestWayInGameMap();
    println(problem.solution(maps))
}