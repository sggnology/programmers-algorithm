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

        var firstList = queue1.toMutableList()
        var secondList = queue2.toMutableList()

        var firstListSum = 0L
        var secondListSum = 0L

        var numberOfOperations = 0

        while(true){

            if(firstList.isEmpty() || secondList.isEmpty()){
                answer = -1
                break
            }

            if(queue1.size * 2 < numberOfOperations){
                answer = -1
                break
            }

//            firstListSum = firstList.sum()
//            secondListSum = secondList.sum()
            firstListSum = getSum(firstList)
            secondListSum = getSum(secondList)

            if(firstListSum == secondListSum){
                answer = numberOfOperations
                break
            }

            if(firstListSum < secondListSum){
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

    private fun getSum(list: MutableList<Int>): Long{

        var sum = 0L

        for(ele in list){
            sum += ele
        }

        return sum
    }

    private fun queue1ToQueue2(queue1: MutableList<Int>, queue2: MutableList<Int>){
        queue2.add(
            queue1.removeAt(0)
        )
    }

    private fun queue2ToQueue1(queue1: MutableList<Int>, queue2: MutableList<Int>){
        queue1.add(
            queue2.removeAt(0)
        )
    }

}