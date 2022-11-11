package programmers.test

import kotlin.math.sqrt

class 숫자카드나누기 {
    fun solution(arrayA: IntArray, arrayB: IntArray): Int {
        val answerList = mutableListOf<Int>()

        /**
         * 설명
         * - 철수와 영희가 가진 숫자카드의 최대공약수를 구한다.
         * - 각자의 공약수로 상대방의 숫자카드가 안나뉘어지는지 확인한다.
         * - 안나뉘어진다면 `answerList`의 원소로 추가한다.
         *
         * - 만약 `answerList`의 크기가 0 이라면 조건에 해당하는 경우가 없음으로 `0`을 return 한다.
         * - 그게 아니라면 `answerList`의 수중 가장 큰 수를 return 한다.
         * */
        val gcdA = getGcd(arrayA)
        val gcdB = getGcd(arrayB)

        if(isAnyElementsNotDividedBy(gcdA, arrayB)){
            answerList.add(gcdA)
        }

        if(isAnyElementsNotDividedBy(gcdB, arrayA)){
            answerList.add(gcdB)
        }

        if(answerList.isEmpty()){
            return 0
        }

        return answerList.maxOf { it }
    }

    /**
     * 설명
     * - 문제의 조건에 해당하는 경우
     * - elements 배열의 원소중 var1 에 의해 `나눠지는 경우` return false
     * - elements 배열의 원소중 var1 에 의해 `나눠지지 않는 경우` return true
     * */
    fun isAnyElementsNotDividedBy(var1: Int, elements: IntArray): Boolean{

        if(var1 == 1){
            return false
        }

        for(element in elements){
            if(element % var1 == 0) return false
        }

        return true
    }

    /**
     * 설명
     * - 만약 `최대 공약수`로도 값이 구해지지 않는다면, 약수를 사용하려고 하였다.
     * */
    fun getDividableNums(var1: Int): List<Int>{
        val dividableNumsList = mutableListOf<Int>(1, var1)
        val tmpVar1 = sqrt(var1.toDouble()).toInt()

        for(i in 2 .. tmpVar1){
            if(var1 % i == 0){
                dividableNumsList.add(i)
                dividableNumsList.add(var1/i)
            }
        }

        return dividableNumsList.sorted().reversed()
    }

    /**
     * 설명
     * - 최대공약수 구하는 함수
     * */
    fun getGcd(elements: IntArray): Int{

        /**
         * 설명
         * - 아래 주석되어있는 방법과는 다르게 List 자료형을 사용하지 않는다.
         * - 기존의 값을 참조하면서 최대공약수를 구한다.
         * */
        var tmpGcd = elements.first()

        for(index in elements.indices){
            tmpGcd = uclideHojeFunc(tmpGcd, elements[index])
        }

        return tmpGcd

        /**
         * 설명
         * - 값을 구할 수는 있으나, `시간 초과`가 발생하는 문제
         * - 리스트를 선언하여 값을 넣고 빼는 방식으로 구현하였으나, 리스트에 해당 연산들은 굉장히 비싸다.
         * - ex) 500,000 개를 넣고빼면 컴퓨터마다 다르겠지만 38초 정도 소요되는것을 확인 할 수 있었다.
         * */
//        val elementList = elements.toMutableList()
//
//        while(1 < elementList.size){
//
//            val e1 = elementList.removeAt(0)
//            val e2 = elementList.removeAt(0)
//
//            val uclideHojeValue = uclideHojeFunc(e1, e2)
//
//            elementList.add(0, uclideHojeValue)
//
//            elementList.add(
//                0,
//                uclideHojeFunc(elementList.removeFirst(), elementList.removeFirst())
//            )
//        }
//
//        return elementList.first()
    }

    /**
     * 설명
     * - 유클리드 호제법을 사용할때 반드시 `나눠지는 수`가 `나누는 수` 보다 커야 한다.
     * - 따라서, 아래와 같이 수 var1, var2 를 넣으면 큰 숫자가 `0`번째 인덱스를 가지는 Pair 를 리턴한다.
     * */
    fun getReverseSortedPair(var1: Int, var2: Int): Pair<Int, Int>{
        if(var1 < var2){
            return Pair(var2, var1)
        }
        else{
            return Pair(var1, var2)
        }
    }

    /**
     * 설명
     * - `유클리드 호제` 알고리즘으로 최대공약수를 구할 수 있다.
     * - 참고 : https://ko.wikipedia.org/wiki/%EC%9C%A0%ED%81%B4%EB%A6%AC%EB%93%9C_%ED%98%B8%EC%A0%9C%EB%B2%95
     * */
    fun uclideHojeFunc(var1: Int, var2: Int): Int {

        var (tmpVar1, tmpVar2) = getReverseSortedPair(var1, var2)

        while (true) {
            val isDividedWithoutRest = tmpVar1 % tmpVar2 == 0

            if (isDividedWithoutRest) {
                return tmpVar2
            } else {
                tmpVar1 = tmpVar2.also { tmpVar2 = tmpVar1 }
                tmpVar2 = tmpVar2 % tmpVar1
            }
        }
    }
}