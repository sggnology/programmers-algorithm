package programmers.test

class 혼자놀기의달인 {
    fun solution(cards: IntArray): Int {
        var answer = 0

        /**
         * Parameters
         * - visited : cards 중 방문 기록용
         * - numberOfCasesForNumberGroups : cards 중에서 `그룹`이 될 수 있는 경우의 수
         * */
        val visited = BooleanArray(cards.size){false}
        val numberOfCasesForNumberGroups = mutableListOf<MutableList<Int>>()

        /**
         * 설명
         * - cards 중에서 방문하지 않은 index 추출
         * -- 만약 모두 방문했다면(==-1) 모든 경우의 수를 확인했음으로 iteration 종료
         * - 경우의 수 세팅(groupOfCards)
         * - 내부 iteration 에서 검색될 index 세팅(searchIndex)
         *
         * - 문제에서 박스는 1 부터 이름을 부여함으로 index 에 -1 처리함(nextTargetIndex)
         * -- 만약 방문했다면, 해당 경우의 수 가 방문할 박스를 모두 방문한 것 임으로 내부 iteration 탈출
         * - 검색한 박스의 내용이 다음 검색될 index 이다.
         * */
        while(true){

            val closestAtFrontIndex = findIndexClosestAtFront(visited)

            if(closestAtFrontIndex == -1){
                break
            }

            val groupOfCards = mutableListOf<Int>()

            var searchIndex = closestAtFrontIndex

            while(true){

                val nextTargetIndex = getNextTargetIndex(searchIndex)
                val nextTarget = cards[nextTargetIndex]

                if(visited[nextTargetIndex]){
                    break
                }

                visited[nextTargetIndex] = true
                groupOfCards.add(nextTarget)

                searchIndex = nextTarget
            }

            numberOfCasesForNumberGroups.add(groupOfCards)
        }

        if(isFinishedFromFirstShot(numberOfCasesForNumberGroups)){
            return 0
        }

        answer = calculateMaxScore(numberOfCasesForNumberGroups)

        return answer
    }

    /**
     * 설명
     * - 상자는 1부터 시작함으로 index 로 변환하는 과정이 필요하다.
     * */
    private fun getNextTargetIndex(target: Int): Int{
        return target - 1
    }

    /**
     * 설명
     * - 전부를 검색하기 위해 visited 의 `0`번째 index 부터 검사하여 제일 `0`에 가까운 방문하지 않은 index 를 반환
     * - 전부 방문했다면 `-1`을 반환
     * */
    private fun findIndexClosestAtFront(visited: BooleanArray): Int{
        for((index, visitedStatus) in visited.withIndex()){
            if(!visitedStatus){
                return index + 1
            }
        }

        return -1
    }

    /**
     * 설명
     * - 문제 조건중에서 처음검색에서 cards 전부를 탐색했다면 `0`이 정답이다.
     * */
    private fun isFinishedFromFirstShot(result: MutableList<MutableList<Int>>): Boolean{
        return result.size == 1
    }

    /**
     * 설명
     * - 선별된 그룹들중 제일 큰 두개의 곱셈이 정답
     * */
    private fun calculateMaxScore(result: MutableList<MutableList<Int>>): Int{
        val reverseSortedNumberOfCasesForNumberGroups = getReversedEachListSize(result)

        return reverseSortedNumberOfCasesForNumberGroups[0] * reverseSortedNumberOfCasesForNumberGroups[1]
    }

    private fun getReversedEachListSize(list: MutableList<MutableList<Int>>): List<Int>{
        return list.map { it.size }.sortedDescending()
    }
}