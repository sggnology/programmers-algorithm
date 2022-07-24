package programmers.highscorekit

import kotlin.math.roundToInt

/**
 * 설명
 * - 프로그래머스 > 고득점 kit > 이분탐색 > level3 > N으로 표현
 * - 경로 : https://school.programmers.co.kr/learn/courses/30/lessons/42895?language=kotlin
 *
 * */

class ExpressedByN {
    fun solution(N: Int, number: Int): Int{
        var answer = 0

        if(N == number){
            return 1;
        }

        val oneTimesUsed = listOf(5)
        val twoTimesUsed = listOf(55, 5+5, 5-5, 5*5, 5/5)
        val threeTimesUsed = listOf(
            listOf(555),
            twoTimesUsed.map { two -> oneTimesUsed.map { one -> two + one } }.flatten(),
            twoTimesUsed.map { two -> oneTimesUsed.map { one -> two - one } }.flatten(),
            twoTimesUsed.map { two -> oneTimesUsed.map { one -> two * one } }.flatten(),
            twoTimesUsed.map { two -> oneTimesUsed.map { one -> if(one == 0) return 0 else two / one } }.flatten(),
        ).flatten()
        val fourTimesUsed = listOf(
            listOf(5555),
            threeTimesUsed.map { three -> oneTimesUsed.map { one -> three + one } }.flatten(),
            threeTimesUsed.map { three -> oneTimesUsed.map { one -> three - one } }.flatten(),
            threeTimesUsed.map { three -> oneTimesUsed.map { one -> three * one } }.flatten(),
            threeTimesUsed.map { three -> oneTimesUsed.map { one -> if(one == 0)  0 else three / one } }.flatten(),
            twoTimesUsed.map { firstTwo -> twoTimesUsed.map { two -> firstTwo + two } }.flatten(),
            twoTimesUsed.map { firstTwo -> twoTimesUsed.map { two -> firstTwo - two } }.flatten(),
            twoTimesUsed.map { firstTwo -> twoTimesUsed.map { two -> firstTwo * two } }.flatten(),
            twoTimesUsed.map { firstTwo -> twoTimesUsed.map { two -> if(two == 0)  0 else firstTwo / two } }.flatten(),
        ).flatten()

        println(threeTimesUsed.map { three -> oneTimesUsed.map { one -> three + one } }.flatten())

        println(fourTimesUsed.size)

        println(fourTimesUsed.indexOf(5))

        val sameDigitList = genSameDigitNumber(N)

        val numberConditionMap = mapOf<Int, List<Int>>()

        for(times in 1 .. 9){

        }

        return -1;
    }

    fun isThereValue(number: Int, checkList: List<Int>): Boolean{
        if(checkList.contains(number)){
            return true
        }

        return false
    }

    fun genSameDigitNumber(theNumber: Int): List<Int>{

        val sameDigitList = MutableList<String>(9){theNumber.toString()}

        for(i in sameDigitList.indices){
            for(times in 0 until i){
                sameDigitList[i] = sameDigitList[i].plus(theNumber)
            }
        }

        return sameDigitList.map { it.toInt() }
    }

    fun genNumberOfCases(times: Int): List<List<Int>>{

        val startPoint = (times/2.0).roundToInt()
        val endPoint = times

        val resultList = mutableListOf<List<Int>>()

        for(time in startPoint until endPoint){
            resultList.add(listOf(time, endPoint - time))
        }

        return resultList
    }
}