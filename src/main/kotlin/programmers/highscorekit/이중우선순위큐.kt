package programmers.highscorekit

import javafx.collections.transformation.SortedList
import java.util.*

/**
 * 설명
 * - 프로그래머스 > 고득점 kit > 이중우선순위큐
 * - 경로 : https://school.programmers.co.kr/learn/courses/30/lessons/42628
 *
 * */

class 이중우선순위큐 {
    /**
     * 설명
     * - 정렬 된다면 가장 큰 값과 가장 작은 값을 인덱스로 찾아 낼 수 있다.
     * - 따라서 정렬을 시킨 뒤에 해당 원소를 삭제 시킴으로써 해결할 수 있다.
     * */
    fun solution(operations: Array<String>): IntArray {
        val sortedList = mutableListOf<Int>()

        operations.forEach { operation ->

            if(operation.contains("I")){
                sortedList.add(operation.split(" ")[1].toInt())
                sortedList.sort()
            }
            else{
                if(sortedList.isNotEmpty()){
                    if(operation == "D 1"){
                        sortedList.removeAt(sortedList.size - 1)
                    }
                    else{
                        sortedList.removeAt(0)
                    }
                }
            }
        }

        if(sortedList.isEmpty()){
            println(intArrayOf(0,0).toList())
            return intArrayOf(0,0)
        }
        else{
            println(intArrayOf(sortedList[sortedList.size - 1], sortedList[0]).toList())
            return intArrayOf(sortedList[sortedList.size - 1], sortedList[0])
        }

    }

    /**
     * 설명
     * - priorityQueue 를 사용하여 최댓값(hi)과 최솟값(lo)을 저장하는 변수를 따로 두었다.
     * - 최댓값을 삭제할 경우 hi 에서 poll() 하여 제일 큰 값을 배제하고
     * - 최솟값을 삭제할 경우 lo 에서 poll() 하여 제일 작은 값을 배제하였다.
     * - 그런데 이렇게 하면 특수한 경우에 문제가 풀리지 않게 된다.
     *
     * 예외 테스트 케이스
     * - 파라미터 : ["I 10", "I 20", "D 1", "I 30", "I 40", "D -1", "D -1"], 정답 : [40,40] -> 틀린 정답 [40, 30]
     *
     * 예외 발생 이유
     * - 애초부터 모든 값을 확인하며 처리하는 로직이 아니기에 구멍이 있을거라고 생각하였다.
     * - 그 구멍은 위의 경우처럼 n번 값을 넣은뒤 n-1번 값을 빼면 발생한다.
     * - 예외의 경우 hi : [40,30,10], lo : [30,40] 을 가지게 된다.
     * - 따라서 틀린 정답을 도출하게 됨으로 틀린 방법이다.
     * */
//    fun solution(operations: Array<String>): IntArray {
//        var modCnt = 0
//
//        val priorityQueueLowest = PriorityQueue<Int>()
//        val priorityQueueHighest = PriorityQueue<Int>(Collections.reverseOrder())
//
//        operations.forEach { operation ->
//
//            if(operation.contains("I")){
//                priorityQueueLowest.add(operation.split(" ")[1].toInt())
//                priorityQueueHighest.add(operation.split(" ")[1].toInt())
//            }
//            else if(operation == "D 1"){
//                modCnt++
//                priorityQueueHighest.poll()
//            }
//            else{
//                modCnt++
//                priorityQueueLowest.poll()
//            }
//        }
//
//        if(priorityQueueHighest.size + priorityQueueLowest.size <= modCnt){
//            println(intArrayOf(0,0).toList())
//            return intArrayOf(0,0)
//        }
//        else{
//            println(intArrayOf(priorityQueueHighest.peek(), priorityQueueLowest.peek()).toList())
//            return intArrayOf(priorityQueueHighest.peek(), priorityQueueLowest.peek())
//        }
//
//    }
}