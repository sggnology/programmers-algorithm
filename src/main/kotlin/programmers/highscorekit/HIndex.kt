package programmers.highscorekit

/**
 * 설명
 * - 프로그래머스 > 고득점 kit > 정렬 > level2 > H-Index
 * - 경로 : https://school.programmers.co.kr/learn/courses/30/lessons/42747
 * */
class HIndex {
    fun solution(citations: IntArray): Int{
//        val sortedCitations = citations.sorted().toIntArray()
//
//        val sortedCitationsLength = citations.size
//        var midIdx: Int = sortedCitationsLength/2
//
//        while((sortedCitationsLength - midIdx) < sortedCitations[midIdx]){
//            if(midIdx == 0){
//                break
//            }
//            midIdx -= 1
//        }
//
//        println(sortedCitations[midIdx])
//
//        return sortedCitationsLength - midIdx

//        val sortedList = citations.sorted()
//
//        val length = sortedList.size
//        val midIdx = length/2
//        val midValue = sortedList[midIdx]
//
//        if(length < midIdx){
//            return length
//        }
//        else{
//            for(possibleAnswer in midValue..1){
//                if((length - midIdx))
//            }
//        }

        val reverseSortedList = citations.sorted().reversed()
        var index = 0

        for(value in reverseSortedList){
            if(value <= index){
                break
            }
            index += 1
        }

        println(index)

        return index

    }
}