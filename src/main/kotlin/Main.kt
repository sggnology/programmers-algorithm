import finalBackendTest2022.세번째문제
import programmers.highscorekit.단어변환
import programmers.test.귤고르기
import programmers.test.숫자카드나누기
import programmers.test.연속_부분_수열_합의_개수
import programmers.test.우박수열_정적분
import programmers.코딩테스트입문.외계행성의나이
import java.time.Duration
import java.time.LocalDateTime
import java.time.Period
import java.util.*

fun main(args: Array<String>) {

//    귤고르기().solution(6, intArrayOf(1, 3, 2, 5, 4, 5, 2, 3))

    세번째문제().solution(
        arrayOf("1 2 3 4 5 6 7 8 9 10", "2 8 11"),
        1,
        11
    )

    val obj1 = Obj1(var1 = "test")
    val obj2 = Obj1(var1 = "test")
    val var1: String = "test"
    val var2: String = "test"

    println(obj1 == obj2)
    println(Objects.equals(obj1, obj2))

    println(obj1.var1 == var1)
    println(var1 == var2)

    println(var1.equals(var2))

    println(Objects.equals(obj1.var1, var1))

}

data class Obj1(
    val var1: String
)