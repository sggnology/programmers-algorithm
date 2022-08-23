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

        var maxCounts: Long = queue1.size.toLong() * queue1.size.toLong()

        var firstList: Queue<Int> = LinkedList(queue1.toMutableList())
        var secondList: Queue<Int> = LinkedList(queue2.toMutableList())

        var firstListSum = getSum(firstList)
        var secondListSum = getSum(secondList)
        var totalSum = firstListSum + secondListSum

        if(totalSum % 2 == 1L){
            return -1
        }

        var numberOfOperations = 0L

        while(true){

            if(firstList.isEmpty() || secondList.isEmpty()){
                return -1
            }

            if(maxCounts < numberOfOperations){
                return -1
            }

            firstListSum = getSum(firstList)

            if(firstListSum == totalSum/2){
                answer = numberOfOperations.toInt()
                break
            }
            else if(firstListSum < totalSum/2){
                queue2ToQueue1(firstList, secondList);
            }
            else{
                queue1ToQueue2(firstList, secondList);
            }

            numberOfOperations += 1

        }

//        print("answer : $answer")

        return answer
    }

    private fun getSum(list: Queue<Int>): Long{

        var sum = 0L

//        for(ele in list){
//            sum += ele
//        }

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