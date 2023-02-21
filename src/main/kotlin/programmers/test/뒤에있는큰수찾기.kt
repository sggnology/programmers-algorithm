package programmers.test

import java.util.Stack

class 뒤에있는큰수찾기 {
    fun solution(numbers: IntArray): IntArray {

        val result = IntArray(numbers.size){-1}
        val stack = Stack<Pair<Int, Int>>()

        for(numberIndex in numbers.indices){
            val compareNumber = numbers[numberIndex]

            if(stack.isEmpty()){
                stack.push(Pair(numberIndex, compareNumber))
            }
            else{
                /**
                 * 설명
                 * - stack 이 비어있을때 peek, pop 을 시도하면 EmptyStackException 이 발생한다.
                 * - 따라서 while 조건문 내부에서 stack.peek() 를 바로 시도하게 되면 안된다.
                 * - 그렇기 떄문에 stack.empty() 를 선행조건으로 두어 먼저 검사하게 처리하여야 한다.
                 * -
                 * */
                while(stack.isNotEmpty() && stack.peek().second < compareNumber){
                    val lastStackedPair = stack.pop()
                    result[lastStackedPair.first] = compareNumber
                }
                stack.push(Pair(numberIndex, compareNumber))
            }
        }

        return result
    }

    /**
     * 설명
     * - 이중 for 문 풀이방법
     * */
//    fun solution(numbers: IntArray): IntArray {
//
//        val solution = IntArray(numbers.size){0}
//        solution[solution.size - 1] = -1
//
//        val maxNumberInNumbers = numbers.maxOf { it }
//
//        for(outerNumberIndex in numbers.indices){
//            val targetNumber = numbers[outerNumberIndex]
//
//            for(innerNumberIndex in outerNumberIndex + 1 until numbers.size){
//                val compareNumber = numbers[innerNumberIndex]
//
//                if(targetNumber == maxNumberInNumbers){
//                    solution[outerNumberIndex] = -1
//                    break
//                }
//
//                if(targetNumber < compareNumber){
//                    solution[outerNumberIndex] = compareNumber
//                    break
//                }
//
//                if(innerNumberIndex == numbers.size - 1){
//                    solution[outerNumberIndex] = -1
//                }
//            }
//        }
//
////        println(solution.toList())
//
//        return solution
//    }
}