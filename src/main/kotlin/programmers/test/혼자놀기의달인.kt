package programmers.test

class 혼자놀기의달인 {
    fun solution(cards: IntArray): Int {
        var answer: Int = 0

        val visited = BooleanArray(cards.size){false}
        val numberOfCasesForNumberGroups = mutableListOf<MutableList<Int>>()

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

        println(numberOfCasesForNumberGroups)

        if(numberOfCasesForNumberGroups.size == 1){
            return 0
        }

        val reverseSortedNumberOfCasesForNumberGroups = numberOfCasesForNumberGroups.map { it.size }.sortedDescending()

        return reverseSortedNumberOfCasesForNumberGroups[0]*reverseSortedNumberOfCasesForNumberGroups[1]
    }

    private fun getNextTargetIndex(target: Int): Int{
        return target - 1
    }

    private fun findIndexClosestAtFront(visited: BooleanArray): Int{
        for((index, visitedStatus) in visited.withIndex()){
            if(!visitedStatus){
                return index + 1
            }
        }

        return -1
    }
}