package programmers.test

import kotlin.math.*

class 점찍기 {
    fun solution(k: Int, d: Int): Long {

        var count = 0L

        val dPowered = power(d)

        for(x in (0 .. d step k)){
            count += getMaxYCoordinatesCounts(dPowered-power(x), k)
        }

        return count
    }

    /**
     * 설명
     * - Math 패키지를 사용하고 싶지 않아서 만든 Math.pow 대체 함수
     * - 문제에서 d 를 제곱하면 int 의 범위를 넘어감으로 Long 으로 반환하게끔 구현
     *
     * 주의사항
     * - `틀리게 된 함수` 사용시 `number * number`의 연산이 먼저 일어남으로 int 의 범위를 넘어가게되면 음수가 되게된다.
     * -- ex) `51_000 * 51_000 = 2_601_000_000` 는 int 의 범위를 넘어감으로 `..= -1_693_967_296` 임으로 계산 오류 나게됨
     * */
    private fun power(number: Int): Long{
        // 틀리게 된 함수
//        return (number * number).toLong()
        return (number.toLong() * number.toLong())
    }

    private fun getMaxYCoordinatesCounts(beforeScaledNumber: Long, k: Int): Int{
        /**
         * 설명
         * - x 좌표에 따르는 y 좌표의 갯수를 반환한다.
         *
         * Parameter
         * - sqrtOfMaxYCoordinate : d 를 기준으로 원을 그렸을때 좌표 평면에서 가질수 있는 Y 의 최댓값(Double) 값
         * - dropUnderDecimalPoint : 양의 정수를 고려하고 있음으로, 필요없는 소수점 버림
         * - getScaleMaxY : 제일큰 y 값 보다 작지만 k 의 배수중 가장 높은 값
         * - plusOneBecauseNotCountZero : k 개로 나누면 갯수가 추출되지만, 0은 count 안됨으로 +1 한 값
         * */

//        return Math.floorDiv(floor(sqrt(beforeScaledNumber.toDouble())).toInt(), k).times(k)
        val sqrtOfMaxYCoordinate = sqrt(beforeScaledNumber.toDouble())
        val dropUnderDecimalPoint = floor(sqrtOfMaxYCoordinate)
        val getScaledMaxY = Math.floorDiv(dropUnderDecimalPoint.toInt(), k).times(k)
        val plusOneBecauseNotCountZero = getScaledMaxY.div(k).plus(1)

        return plusOneBecauseNotCountZero
    }

    /**
     * 설명
     * - 11, 12, 14 시간 초과된 로직
     * - 2중 for 문 사용시 시간 초과된다.
     * */
//    fun solution(k: Int, d: Int): Long {
//
//        var count = 0L
//
//        val dPowered = power(d.toLong())
//
//        var y = 0L
//        var x = 0L
//
//        var singleTaskCount = 0L
//
//        while(true){
//
//
//            val yPowered = power(y)
//
//            if(power(x).plus(power(y)) > dPowered){
//                break
//            }
//
//            while(true){
//
//                val xPowered = power(x)
//
//                if(xPowered.plus(yPowered) <= dPowered){
//                    singleTaskCount += 1
//                    x += k
//                }
//                else{
//                    count += singleTaskCount * 2 - 1
//                    singleTaskCount = 0L
//                    y += k
//                    x = y
//                    break
//                }
//            }
//        }
//
//        return count
//    }
}