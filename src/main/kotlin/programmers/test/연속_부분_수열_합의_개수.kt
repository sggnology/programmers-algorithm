package programmers.test

class 연속_부분_수열_합의_개수 {
    fun solution(elements: IntArray): Int {
        val sequentialSet = mutableSetOf<Int>()

        /**
         * 수도 코드
         *
         * list = [
         *  elements[0], ... , elements[4],
         *  elements[0] + elements[1], ... , elements[4] + elements[0],
         *  ...
         * ]
         * */

        getSolution(sequentialSet, elements)

        return sequentialSet.size
    }

    /**
     * 설명
     * - 회전 수열은 그것의 크기(길이)로 인해 다른 값이 나온다.
     * - 마지막 값은 전체의 합과 동일하기에 모두 같은 값이 됨으로 하나만 계산한다.
     * */
    private fun getSolution(solutionSet: MutableSet<Int>, elements: IntArray){
        for(sequenceLength in 1 until elements.size){
            processSequentialSet(sequenceLength, elements, solutionSet)
        }

        solutionSet.add(elements.sum())
    }

    /**
     * 설명
     * - 회전 수열의 합은 각각의 길이에 따라 다른 합을 가지게 된다.
     * - 예제에서와 같이 길이가 5인 수열을 제외하고 나머지 길이의 수열은 elements 길이만큼 값을 가지게 된다.
     *
     * 특이사항
     * - 수열의 처음과 끝이 붙어있음으로 해당 수열의 크기보다 참조 인덱스 값이 커지면 mod(%) 하여 처리한다.
     *
     * 예시
     * - elements : [1,2,3,4,5]
     * - 길이가 1인 회전수열 : [1,2,3,4,5]
     * - 길이가 2인 회전수열 : [3(1+2), 5(2+3), 7(3+4), 9(4+5), 6(5+1)]
     * - ...
     * - 길이가 5인 회전수열 : [15(1+2+3+4+5)]
     * */
    private fun processSequentialSet(sequenceLength: Int, elements: IntArray, solutionSet: MutableSet<Int>){

        val elementSize = elements.size

        for(index in 0 until elementSize){

            var sum = 0

            for(startIndex in index until (index + sequenceLength)){
                val circularIndex = startIndex % elementSize

                sum += elements[circularIndex]
            }

            solutionSet.add(sum)
        }
    }
}