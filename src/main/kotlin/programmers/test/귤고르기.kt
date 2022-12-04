package programmers.test

class 귤고르기 {
    fun solution(k: Int, tangerine: IntArray): Int {
        var answer: Int = 0

        var tmpK = k

        /**
         * 알고리즘 아이디어
         * - 최소한의 갯수로 귤을 넘겨줘야 된다면 제일 많은 귤의 갯수부터 소진시키자.
         *
         * 설명
         * - 귤의 크기에 맞게끔 Map 자료구조로 변형
         * - 귤의 갯수로 치환한뒤
         * - 크기의 역순으로 정렬한다.
         *
         * 부연설명
         * - kotlin 은 다양한 변형함수를 제공함으로 적절히 사용하면 되겠다.(sorted().reversed() = sortedDescended())
         * - 아래 경우에서는 정렬전에 size 로 transformation(map) 하는 과정이 더 빠르겠다 싶었다.
         * */
//        val obj1 = tangerine
//            .groupBy { it }
//            .values.sortedBy { it.size }.reversed()
//            .map { it.size }
        val obj1 = tangerine
            .groupBy { it }
            .values
            .map { it.size }
            .sortedDescending()

        for((eleIndex, ele) in obj1.withIndex()){
            tmpK -= ele
            if(tmpK <= 0){
                answer = eleIndex + 1
                break
            }
        }

        return answer
    }
}