package programmers.test

/**
 * 설명
 * - 투 포인터 알고리즘을 사용하여 문제를 풀이하였다.
 * - 투 포인터 알고리즘은, 구간합을 구할 때 사용한다.
 * */
class 연속된_부분_수열의_합 {
    fun solution(sequence: IntArray, k: Int): IntArray {

        val answerList = mutableListOf<List<Int>>()

        var sum = sequence.first()

        var start = 0
        var end = 0

        val sequenceSize = sequence.size

        while(end < sequenceSize){

            /**
             * 설명
             * - end가 sequenceSize와 같아졌을 때 sum이 k보다 작으면 더 이상 계산할 필요가 없다.
             * */
            if(end == sequenceSize-1){
                if(sum < k){
                    break
                }
            }

            if(sum <= k){
                if(sum == k){
                    answerList.add(listOf(start, end))
                }
                end++

                if(end == sequenceSize){
                    break
                }

                /**
                 * 설명
                 * - 구간 합계에 대한 상태를 매번 구하지 않고, 상태에 따라 다르게 계산한다.
                 * - 아래에서는, end 가 한단계 높아짐으로 구간합에서 높아진 end 인덱스에 값을 더한다.
                 * */
                sum += sequence[end]
            }
            else{
                /**
                 * 설명
                 * - 아래에서는, start 가 한단계 높아짐으로, 구간합에서 높아지기전 start 인덱스의 값을 뺀다.
                 * */
                sum -= sequence[start]
                start++
            }
        }

        return answerList.sortedBy { it[1] - it[0] }.first().toIntArray()
    }
}