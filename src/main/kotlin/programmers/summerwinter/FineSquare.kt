package programmers.summerwinter

import kotlin.math.ceil
import kotlin.math.floor

/**
 * 설명
 * - 프로그래머스 > Summer/Winter Coding(2019) > 멀쩡한 사각형
 * - 경로 : https://school.programmers.co.kr/learn/courses/30/lessons/62048
 *
 * */

class FineSquare {
    /**
     * 설명
     * - 문제에서는 왼쪽위에서 아래 대각선으로 가로지르는데 이는 왼쪽 아래 즉,[0,0](좌표)에서 시작해서 오른쪽 위까지 가로지르는 것과 같은 결과값을 가진다.
     * - 기울기: h/w, 인 1차 방정식 그래프를 그릴 수 있다는 것을 알 수 있다.
     * - 그림을 보았을 때, y 값이 int 인 좌표지점에서 작은 사각형(sub square)이 그려지는 것을 알 수 있다.
     * - 정답 : (작은 사각형 내부의 사이즈 - 작은 사각형을 지나는 사각형의 수) * 전체 사각형의 길이(w)/작은 사각형의 x 좌표 (작은 사각형의 개수)
     * */
    fun solution(w: Int, h: Int): Long{
        // 모든 사각형의 개수
        val allSquareCounts:Long = w*h.toLong()

        // y 값이 int 인 첫번째 좌표를 위한 변수
        val firstFineCoordinate = mutableListOf<Int>()

        /**
         * 설명
         * - y 값이 int 인 첫번째 좌표를 찾기 위한 동작
         *
         * 흐름
         * - 시작지점이 [0,0](좌표)임으로, 1부터 작은 사각형을 찾는다.
         * */
        for(x in 1 ..w){

            val equationResult = equation(w, h, x)

            if(isInt(equationResult)){
                firstFineCoordinate.add(x)
                firstFineCoordinate.add(equationResult.toInt())
                break
            }
        }

        // 작은 사각형의 총 개수
        val subSquareCounts = w / firstFineCoordinate[0]

        // 작은 사각형 내부의 가로질러지는 사각형의 개수
        var subSquareCrossedSquareCounts = 0;

        /**
         * 설명을 위한 변수
         * - 작은 사각형 좌표 [w', h']
         *
         * 설명
         * - x 좌표는 양의 정수임으로 0..w 까지이다.
         * - 작은 사각형의 크기는 (1*h')*w'
         * - 즉, x 좌표의 제일 작은 단위인 `1`마다 지나는 값을 구해줄 것이다.
         *
         * 예시
         * - 기울기가 3/2 인 그래프가 있다고 할때 제일 작은 사각형의 첫번째로 만나는 y 값이 int 인 좌표는 [2,3]이다.
         * - 해당 기울기의 그래프가 지나는 좌표는 다음과 같다, [0,0], [1,3/2], [2,3]
         * - 제일 작은 단위 기준에서 보자면, [0,0], [1,3/2] 이다.
         * - 이 때, 우리는 제일 작은 단위 기준에서 3/2이 1와 2 사이를 지나는 것을 알 수 있다.
         * - 즉, 제일 작은 단위에서 2개의 작은 사각형을 지난다는 것을 알 수 있다.
         * - 결과로 도출된 것을 식으로 표현하자면
         * - 식 : 3/2 의 높임처리(ceil) - 0 의 낮음처리(floor), 이다.
         * */
        for(subSquareX in 1 .. firstFineCoordinate[0]){
            // 작은 사각형에서 제일 작은 단위일때 가로질러지는 사각형의 개수
            val subSquareSingleUnitSize = ceil(equation(w, h, subSquareX)) - floor(equation(w, h, subSquareX - 1))
            subSquareCrossedSquareCounts += subSquareSingleUnitSize.toInt()
        }

        // 작은 사각형 내부에서 가로질러지는 모든 사각형의 개수
        val crossedSubSquareCounts = subSquareCrossedSquareCounts * subSquareCounts

//        println(crossedSubSquareCounts)
//        println(allSquareCounts - crossedSubSquareCounts)

        // 가로질러지지 않은 사각형의 모든 개수
        val notCrossedSquareCounts = allSquareCounts - crossedSubSquareCounts

        return notCrossedSquareCounts
    }

    fun equation(w: Int, h: Int, x: Int): Double {

        return x*h.toDouble()/(w.toDouble())
    }

    fun isInt(cmpNum: Double): Boolean{

        if(ceil(cmpNum) == floor(cmpNum)){
            return true
        }

        return false
    }

//    /**
//     * 자료형으로 인한 오류의 종류 1
//     * - 6번 테스트의 경우 에러가 발생한다.
//     * */
//    fun solution(w: Int, h: Int): Long{
//        val allSquareCounts:Long = w*h.toLong()
//
//        val firstFineCoordinate = mutableListOf<Int>()
//
//        /**
//         * 오류
//         * - 정확히는 모르겠으나, 자료형에 의한 오류로써 coefficient(계수) 를 구하고 방정식 값을 구하고자 하면 에러가 난다.
//         * (근데 자료형 오류면 런타임 에러라도 뱉어줘야 하는거 아닌가..)
//         * */
//        val coefficient = getCoefficient(w, h)
//
//        for(x in 1 ..w){
//
//            val equationResult = equation(coefficient, x)
////            val equationResult = equation(w, h, x)
//
//            if(isInt(equationResult)){
//                firstFineCoordinate.add(x)
//                firstFineCoordinate.add(equationResult.toInt())
//                break
//            }
//        }
//
//        val subSquareCounts = w / firstFineCoordinate[0]
//
//        var subSquareCrossedSquareCounts = 0;
//
//        for(subSquareX in 1 .. firstFineCoordinate[0]){
//            val subSquareSingleUnitSize = ceil(equation(coefficient, subSquareX)) - floor(equation(coefficient, subSquareX - 1))
////            val subSquareSingleUnitSize = ceil(equation(w, h, subSquareX)) - floor(equation(w, h, subSquareX - 1))
//            subSquareCrossedSquareCounts += subSquareSingleUnitSize.toInt()
//        }
//
//        val crossedSubSquareCounts = subSquareCrossedSquareCounts * subSquareCounts
//
//        val notCrossedSquareCounts = allSquareCounts - crossedSubSquareCounts
//
//        return notCrossedSquareCounts
//    }
//
//    fun equation(coefficient: Double, x: Int): Double {
//
//        return x*coefficient
//    }
//
//    fun getCoefficient(w: Int, h: Int): Double{
//        return h/w.toDouble()
//    }
//
//    fun isInt(cmpNum: Double): Boolean{
//
//        if(ceil(cmpNum) == floor(cmpNum)){
//            return true
//        }
//
//        return false
//    }

//    /**
//     * 자료형으로 인한 오류의 종류 2
//     * - 6번은 해결되나 13, 15~17 번에서 에러가 발생한다.
//     * */
//    fun solution(w: Int, h: Int): Long{
//        val allSquareCounts:Long = w*h.toLong()
//
//        val firstFineCoordinate = mutableListOf<Int>()
//
//        for(x in 1 ..w){
//            /**
//             * 오류
//             * - 정확히는 모르겠으나, equation 함수내에서 값을 구할 때 x*h/w.toDouble() 하는데 h 에 toDouble 해주지 않아서 에러가 발생한다.
//             * (근데 자료형 오류면 런타임 에러라도 뱉어줘야 하는거 아닌가..)
//             * (이정도 되면 의문이 드는게, 문제를 풀어냄과 동시에 자료형에 대한 이해 또한 있어야 한다는 건가)
//             * (일단 w,h 가 1억 이하 라는걸 보고 쉽게 정답이 나오지 않을걸 알았지만 이정도 일줄이야..)
//             * */
//            val equationResult = equation(w, h, x)
//
//            if(isInt(equationResult)){
//                firstFineCoordinate.add(x)
//                firstFineCoordinate.add(equationResult.toInt())
//                break
//            }
//        }
//
//        val subSquareCounts = w / firstFineCoordinate[0]
//
//        var subSquareCrossedSquareCounts = 0;
//
//        for(subSquareX in 1 .. firstFineCoordinate[0]){
//            val subSquareSingleUnitSize = ceil(equation(w, h, subSquareX)) - floor(equation(w, h, subSquareX - 1))
//            subSquareCrossedSquareCounts += subSquareSingleUnitSize.toInt()
//        }
//
//        val crossedSubSquareCounts = subSquareCrossedSquareCounts * subSquareCounts
//
//        val notCrossedSquareCounts = allSquareCounts - crossedSubSquareCounts
//
//        return notCrossedSquareCounts
//    }
//
//    fun equation(w: Int, h: Int, x: Int): Double {
//
//        return x*h/w.toDouble()
//    }
//
//    fun isInt(cmpNum: Double): Boolean{
//
//        if(ceil(cmpNum) == floor(cmpNum)){
//            return true
//        }
//
//        return false
//    }
}