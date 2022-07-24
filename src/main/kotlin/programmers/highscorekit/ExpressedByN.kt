package programmers.highscorekit

/**
 * 설명
 * - 프로그래머스 > 고득점 kit > 동적계획법 > level3 > N으로 표현
 * - 경로 : https://school.programmers.co.kr/learn/courses/30/lessons/42895?language=kotlin
 *
 * */

class ExpressedByN {
    fun solution(N: Int, number: Int): Int{
        // 초기 접근
//        var answer = 0
//
//        if(N == number){
//            return 1;
//        }
//
//        val oneTimesUsed = listOf(5)
//        val twoTimesUsed = listOf(55, 5+5, 5-5, 5*5, 5/5)
//        val threeTimesUsed = listOf(
//            listOf(555),
//            twoTimesUsed.map { two -> oneTimesUsed.map { one -> two + one } }.flatten(),
//            twoTimesUsed.map { two -> oneTimesUsed.map { one -> two - one } }.flatten(),
//            twoTimesUsed.map { two -> oneTimesUsed.map { one -> two * one } }.flatten(),
//            twoTimesUsed.map { two -> oneTimesUsed.map { one -> if(one == 0) return 0 else two / one } }.flatten(),
//        ).flatten()
//        val fourTimesUsed = listOf(
//            listOf(5555),
//            threeTimesUsed.map { three -> oneTimesUsed.map { one -> three + one } }.flatten(),
//            threeTimesUsed.map { three -> oneTimesUsed.map { one -> three - one } }.flatten(),
//            threeTimesUsed.map { three -> oneTimesUsed.map { one -> three * one } }.flatten(),
//            threeTimesUsed.map { three -> oneTimesUsed.map { one -> if(one == 0)  0 else three / one } }.flatten(),
//            twoTimesUsed.map { firstTwo -> twoTimesUsed.map { two -> firstTwo + two } }.flatten(),
//            twoTimesUsed.map { firstTwo -> twoTimesUsed.map { two -> firstTwo - two } }.flatten(),
//            twoTimesUsed.map { firstTwo -> twoTimesUsed.map { two -> firstTwo * two } }.flatten(),
//            twoTimesUsed.map { firstTwo -> twoTimesUsed.map { two -> if(two == 0)  0 else firstTwo / two } }.flatten(),
//        ).flatten()
//
//        println(threeTimesUsed.map { three -> oneTimesUsed.map { one -> three + one } }.flatten())
//
//        println(fourTimesUsed.size)
//
//        println(fourTimesUsed.indexOf(5))

        // ================================================

        /**
         * 설명
         * - 같은 수로 자릿수를 채운 수의 조합입니다.
         * - 조건에 따라 9자릿수까지 채워둡니다.
         *
         * 예시
         * - 만약 `N`이 5라면 [5,55,555,5555,...,555555555]
         * */
        val sameDigitList = genSameDigitNumber(N)

        /**
         * 설명
         * - 사용횟수(numberOfUses)를 <key: Int, value: List<Int>> 로 저장할 변수입니다.
         *
         * 예시(N 이 5일때)
         * - 첫번째 사용횟수
         * -- key: 1, value: [5]
         * - 두번째 사용횟수
         * -- key: 2, value: [55, 5+5, 5-5, 5*5, 5/5]
         * */
        val numberOfUsesMap = mutableMapOf<Int, List<Int>>()

        /**
         * 설명
         * - 첫번째 사용횟수 일때는 두번째 이상 조건부터의 로직이 필요하지 않음으로 분기해 두었습니다.
         * - 해당 조건을 통해 자릿수가 한개인 경우를 걸러냅니다.
         * */
        numberOfUsesMap[1] = listOf(N)

        if(checkCondition(number, numberOfUsesMap[1]!!)){
            return 1
        }

        /**
         * 설명
         * - 이전 결과값을 토대로 다음 결과값을 예측합니다.
         * - 조건 `number 는 1이상 32,000이하입니다.` 로 인해 음수는 고려하지 않을 것 입니다.(착오.. 아래 genNumberOfCases 함수 에서 문제해결 설명 읽고올것)
         * - 조건 `최솟값이 8보다 크면 -1을 return 합니다.` 에 의해 2부터 8까지를 기준으로 완전탐색을 합니다.
         *
         *
         * 변수 설명
         * - numberOfCases : times 번 사용해서 표현할때의 경우의 수 ex) 4번 일 경우: [1,3], [2,2], [3,1]
         * - numberOfUses : times 번 사용해서 표현할때의 값
         * - sameDigit : 사용횟수가 times 일 경우 모든 자릿수를 N 으로 채운 값 ex) times: 4, N: 5 일경우 -> 5555
         *
         * 예시(N 이 5일때)
         * - 1번 사용해서 표현 가능한 수는 [5] 하나 입니다.
         * - 2번 사용해서 표현 가능한 수는 `55`를 제외한 1번 사용해서 표현한 수의 사칙연산 조합입니다.
         * - 3번 사용해서 표현 가능한수는 2번 사용해서 표현한 수와 1번 사용해서 표현한 수의 조합입니다.
         * - 3번 사용해서 표현시 1번 사용해서 표현한 수와 2번 사용해서 표현한 수를 조합하지 않는건 음수는 필요하지 않기 때문입니다.
         * */
        for(times in 2 until 9){
            val numberOfCases = genNumberOfCases(times)
            val numberOfUses = mutableListOf<Int>()
            val sameDigit = sameDigitList[times - 1]

            numberOfUses.add(sameDigit)

            numberOfCases.forEach{case ->
                val firstCondition = numberOfUsesMap[case[0]]
                val secondCondition = numberOfUsesMap[case[1]]

                numberOfUses.addAll(
                    firstCondition!!.map { first -> secondCondition!!.map { second -> first + second } }.flatten() +
                    firstCondition.map { first -> secondCondition!!.map { second -> first - second } }.flatten() +
                    firstCondition.map { first -> secondCondition!!.map { second -> first * second } }.flatten() +
                    firstCondition.map { first -> secondCondition!!.map { second -> if(second == 0)  0 else first / second } }.flatten()
                )
            }

            numberOfUsesMap[times] = numberOfUses

            println("$times : $numberOfUses")

            if(checkCondition(number, numberOfUsesMap[times]!!)){
                println(times)
                return times
            }
        }

        return -1;
    }

    fun checkCondition(number: Int, checkList: List<Int>): Boolean{
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

    /**
     * 문제 발생
     * - 조건 `number 는 1 이상 32,000 이하입니다.` 에 따라 음수는 고려하지 않아도 된다고 판단 하였으나, 그 부분이 착오였다.
     *
     * 예시(5번째 표현시)
     * - 원래 고려하지 않았던 case [2,3] 에 대해서 고려를 해보자.
     * - 2표현에서 값이 `55` 인 값에 대해 3에서는 `[555, 60, 15, 5, 30, 6, -50, -5, 5, -20, 4, 275, 50, 0, 125, 5, 0, 0, 0, 0, 5, 60, 15, 5, 30, 6, 50, 5, -5, 20, -4, 275, 50, 0, 125, 5, 11, 2, 0, 5, 0]`
     * 의 값들중 55보다 낮을 값들이 존재함으로 고려하지 않아야 할 대상이 아니다.
     *
     * 문제 해결
     * - startPoint 변수의 값을 1부터 시작하게끔 수정하자.
     *
     * 경우의 수 결과 값
     * 수정전
     * - [3,1], [2,2]
     * 수정후
     * - [1,3], [2,2], [3,1]
     * */
    fun genNumberOfCases(times: Int): List<List<Int>>{

//        val startPoint = (times/2.0).roundToInt()
        val startPoint = 1
        val endPoint = times

        val resultList = mutableListOf<List<Int>>()

        for(time in startPoint until endPoint){
            resultList.add(listOf(time, endPoint - time))
        }

        return resultList
    }
}