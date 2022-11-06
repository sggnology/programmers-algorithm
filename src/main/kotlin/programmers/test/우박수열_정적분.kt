package programmers.test

import kotlin.math.absoluteValue

class 우박수열_정적분 {
    fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {

        val hailElements = hailSequence(k.toDouble())
        val hailSizes = calcSizeOfHail(hailElements)
        val answer = getSolution(hailElements.size, ranges, hailSizes)

        return answer.toDoubleArray()
    }

    fun getSolution(hailLength: Int, ranges: Array<IntArray>, sizes: List<Double>): List<Double>{

        val solution = mutableListOf<Double>()

        /**
         * 설명
         * - x1 과 x2 가 같다면 해당하는 값이 없기 때문에 0
         * - x1 보다 x2 가 낮다면 문제의 조건에 따라 -1
         * - x1 보다 x2 가 크다면..
         * -- 해당 되는 범위의 크기를 합하여 return
         *
         * Params
         * - x1 : 시작 범위
         * - x2 : 끝 범위
         * */
        for(range in ranges){
            val x1 = range[0]
            val x2 = hailLength - 1 + range[1]

            if(x1 == x2){
                solution.add(0.0)
            }
            else if(x2 < x1){
                solution.add(-1.0)
            }
            else{
                solution.add(
                    sizes.slice(IntRange(x1 , x2 - 1)).sum()
                )
            }
        }

        return solution
    }

    /**
     * 설명
     * - 우박수열이 적용된 값들의 단위가 1인 범위들의 너비 list
     * */
    fun calcSizeOfHail(elements: List<Double>): List<Double>{
        val sizeOfHails = mutableListOf<Double>()

        for(index in 0 until elements.size - 1){

            val d1 = elements[index]
            val d2 = elements[index + 1]

            val size = calcHalfRectangle(d1, d2) + calcRectangle(d1, d2)

            sizeOfHails.add(size)
        }

        return sizeOfHails
    }

    /**
     * 설명
     * - 너비가 1, 높이가 d1,d2 둘의 차이만큼을 가지는 사각형의 크기
     * */
    fun calcHalfRectangle(d1: Double, d2: Double): Double{
        return ((d2 - d1)/2).absoluteValue
    }

    /**
     * 설명
     * - 너비가 1, 높이가 d1,d2 둘중 작은값을 가지는 사각형의 크기
     * */
    fun calcRectangle(d1: Double, d2: Double): Double{
        if(d1 < d2){
            return d1
        }
        return d2
    }

    /**
     * 설명
     * - 우박수열에 따른 결과값 list
     * */
    fun hailSequence(num: Double): List<Double>{

        val hailList = mutableListOf<Double>()
        var tmpNum = num

        while(true){

            if(tmpNum == 1.0) break

            if(tmpNum % 2 == 1.0){
                tmpNum = processOdd(tmpNum)

                hailList.add(tmpNum)
            }
            else{
                tmpNum = processEven(tmpNum)

                hailList.add(tmpNum)
            }
        }

        hailList.add(0, num)

        return hailList

    }

    /**
     * 설명
     * - 우박수열 짝수 접근시 계산법
     * */
    fun processEven(even: Double): Double{
        return even/2.0
    }

    /**
     * 설명
     * - 우박수열 홀수 접근시 계산법
     * */
    fun processOdd(odd: Double): Double{
        return (odd * 3) + 1
    }
}