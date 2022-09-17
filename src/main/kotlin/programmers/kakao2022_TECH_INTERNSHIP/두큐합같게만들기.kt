package programmers.kakao2021_채용연계형인턴십

import java.util.*

/**
 * 설명
 * - 프로그래머스 > 2022 tech internship > 두 큐 합 같게 만들기
 * - 경로 : https://school.programmers.co.kr/learn/courses/30/lessons/118667
 *
 * */
class 두큐합같게만들기 {
    fun solution(queue1: IntArray, queue2: IntArray): Int{
        var answer = -2

        /**
         * 설명
         * - 두큐의 값 교환에 대한, 최악의 수
         *
         * 예
         * - 1번큐: [1,1,1...](300,000개)
         * - 2번큐: [1,1,1..,599999,1](300,000개)
         * - 위 두큐의 값이 같아지려면
         * - 2번큐에서 299,999 번 이동하고
         * - 1번큐에서 599.998 번 이동해야 한다.
         * */
        val oneCycleCounts: Long = queue1.size.toLong() * 3 - 3

        val firstQueue: Queue<Int> = LinkedList(queue1.toMutableList())
        val secondQueue: Queue<Int> = LinkedList(queue2.toMutableList())

        var firstQueueSum = getSum(firstQueue)
        var secondQueueSum = getSum(secondQueue)
        
        val totalSum = firstQueueSum + secondQueueSum
        /**
         * 설명
         * - 두큐합의 절반이다.
         * */
        val halfSum = totalSum / 2

        /**
         * 설명
         * - 예외조건이다.
         * - 두큐합은 홀수가 되면 안된다.
         * */
        if(totalSum % 2 == 1L){
            return -1
        }

        /**
         * 설명
         * - 두큐가 같아지기 위한 동작 횟수
         * */
        var numberOfOperations = 0L

        while(true){

            /**
             * 설명
             * - 만일 한쪽의 큐가 비게된다는것은 값의 균형이 맞지 않는다는 소리와 같으니, `-1`을 return 한다.
             * */
            if(firstQueue.isEmpty() || secondQueue.isEmpty()){
                return -1
            }

            /**
             * 설명
             * - 최악의 경우의 수보다 더 많은 경우를 시도하려 하는것은 더이상 옳은 경우가 없다는 것이다.
             * */
            if(oneCycleCounts < numberOfOperations){
                return -1
            }

            /**
             * 설명
             * - 하나의 큐라도 절반의 값을 가진다면 정답 조건이다.
             * */
            if(firstQueueSum == halfSum){
                answer = numberOfOperations.toInt()
                break
            }
            /**
             * 설명
             * - firstQueue 가 halfSum 보다 작다는 것은 firstQueue < secondQueue 과 같은 의미
             * */
            else if(firstQueueSum < halfSum){
                firstQueueSum += secondQueue.peek()
                secondQueueSum -= secondQueue.peek()
                queue2ToQueue1(firstQueue, secondQueue)
            }
            else{
                firstQueueSum -= firstQueue.peek()
                secondQueueSum += firstQueue.peek()
                queue1ToQueue2(firstQueue, secondQueue)
            }

            numberOfOperations += 1

        }

        return answer
    }

    private fun getSum(list: Queue<Int>): Long{

        var sum = 0L

        sum = list.sumOf { it.toLong() }

        return sum
    }

    private fun queue1ToQueue2(queue1: Queue<Int>, queue2: Queue<Int>){
        queue2.add(
            queue1.remove()
        )
    }

    private fun queue2ToQueue1(queue1: Queue<Int>, queue2: Queue<Int>){
        queue1.add(
            queue2.remove()
        )
    }

}