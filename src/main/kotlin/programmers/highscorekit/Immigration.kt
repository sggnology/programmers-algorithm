package programmers.highscorekit

/**
 * 설명
 * - 프로그래머스 > 고득점 kit > 이분탐색 > level3 > 입국심사
 * - 경로 : https://school.programmers.co.kr/learn/courses/30/lessons/43238
 *
 * */
class Immigration {

    fun solution(n: Int, times: IntArray): Long{
        var answer: Long = 0

        times.sort()

        var start: Long = 0
        var end: Long = times.last().toLong() * n

        /**
         * 특이점
         * - while(start < end) 조건은 왜 불가능할까?
         *
         * 예를 통해 알아보자
         * n : 6
         * times : [7,10]
         * 만약 start : 28, end: 31 이라고 할때를 가정해보자
         * start: 28, end: 31
         * mid: 29
         * end <- mid - 1
         * answer <- 29
         * ---------------
         * start: 28, end: 28 임으로
         * while(start < end) 조건에 의해 while 문을 탈출하게 된다.
         *
         * 결론
         * - 우리는 구할 수 있는 최소값을 구하려고 하기 떄문에 등호가 포함된 부등호를 사용해야 한다.
         * - 위 예에서 29는 답이 될 수 없는것이 29는 7로 완벽히 나눠지지 않고 마찬가지로 10으로도 완벽히 나눠지지 않는다.
         * */
        while(start <= end){
            val mid: Long = (start + end) / 2
            val count = times.fold(0L) {
                    acc, time ->
                acc + (mid / time)
            }

            if(n <= count){
                end = mid - 1
                answer = mid
            }
            else{
                start = mid + 1
            }
        }

        println(answer)
        return answer
    }
}