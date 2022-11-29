package finalBackendTest2022

import java.util.*
import kotlin.collections.HashMap

class 세번째문제 {
    fun solution(subway: Array<String>, start: Int, end: Int): Int {
        var answer: Int = 0

        val subwaySplitList = subway.map { it.split(" ") }

        /**
         * 설명
         * - 현재 정거장이 포함된 인덱스
         * */
        var currPnt = 0

        for((index, s) in subwaySplitList.withIndex()){
            if(s.contains("" + start)){
                currPnt = index
                break
            }
        }

        val answerList = mutableListOf<Int>()

        /**
         * 설명
         * - 방문한 역을 나타내는 변수
         * - List, Array 와 같은 형태가 아닌 Map 의 형태를 가진다.
         *
         * 부연 설명
         * - 역이 무조건 `0` 번역을 포함하지 않은 경우가 있기에
         * - `0`이 포함되지 않는다면 로직에서 햇갈리기 쉬운 `+1` 같은 side effect 를 발생시켜야 한다.(실수하기 쉬움)
         * */
        val visitedMap = mutableMapOf<String, Boolean>()

        val visited = subwaySplitList.flatten().toSet()

        visited.forEach{ visit ->
            visitedMap[visit] = false
        }

        /**
         * Parameters
         * - currSubWaySplitIndex : 현재 정거장이 포함된 index
         * - visited : 방문한 정거장들
         * - count : 환승 수
         * */
        fun DFS(currSubWaySplitIndex: Int, visited: MutableMap<String, Boolean>, count: Int){

//            visited[throughStation] = true

            for(station in subwaySplitList[currSubWaySplitIndex]){

                /**
                 * 설명
                 * - `호선`이 `목적지 정거장`을 포함하고 있다면 조건 완료
                 *
                 * 부연 설명
                 * - 주석의 로직으로 진행하게 된다면, `호선`을 전부 확인해야 하며 O(n)의 시간복잡도를 가지게 될 수 있다.
                 * - 또, `호선`에 포함된 다른 `정거장`을 전부 확인하며 `환승`하게 되는 경우의 수 추가적으로 발생 가능
                 * */
//                if(Objects.equals(station, end.toString())){
//                    answerList.add(count)
//                    return
//                }
                if(subwaySplitList[currSubWaySplitIndex].contains(end.toString())){
                    answerList.add(count)
                    return
                }

                if(visited[station]!!){
                    continue
                }
                else{

                    visited[station] = true

                    /**
                     * 설명
                     * - 현재 정거장을 포함하고 있지 않은 `호선`의 `환승 가능한 다른 호선`을 찾는다.
                     * */
                    val stationIncludeIndexList = mutableListOf<Int>()

                    subwaySplitList.forEachIndexed { index, subwaySplit ->

                        if(subwaySplit.contains(station)){
                            if(currSubWaySplitIndex != index){
                                stationIncludeIndexList.add(index)
                            }
                        }
                    }

                    if(stationIncludeIndexList.isEmpty()){
                        continue
                    }
                    else{
                        /**
                         * 설명
                         * - 환승이 가능하다면 바로 환승
                         * - `HashMap<String, Boolean>(visited).toMutableMap()`을 통해 새로운 visited 를 생성
                         * */
                        stationIncludeIndexList.forEach { changedCurrPnt ->

                            val newVisitedMap = HashMap<String, Boolean>(visited).toMutableMap()

                            DFS(changedCurrPnt, newVisitedMap, count + 1)
                        }
                    }
                }

            }

        }

        DFS(currPnt, visitedMap, 0)

        println(answerList)

        return answer
    }
}

//package finalBackendTest2022
//
//class 세번째문제 {
//    fun solution(subway: Array<String>, start: Int, end: Int): Int {
//        var answer: Int = 0
//
//        val subwaySplit = subway.map { it.split(" ") }
//
//        var currPnt = 0
//        var endPnt = 0
//
//        subwaySplit.forEachIndexed { index, s ->
//            if(s.contains("" + start)){
//                currPnt = index
//            }
//            if(s.contains("" + end)){
//                endPnt = index
//            }
//        }
//
//        val answerList = mutableListOf<Int>()
//        val visited = subwaySplit.flatten().toSet().map { false }.toBooleanArray()
//
//        fun DFS(currSubWaySplitIndex: Int, throughStation: Int, visited: BooleanArray, count: Int){
//
//            visited[throughStation] = true
//
//            for(station in subwaySplit[currSubWaySplitIndex]){
//
//                if(visited[station.toInt()]){
//                    continue
//                }
//                else{
////                    visited[station.toInt()] = true
//
//                    val stationIncludeIndexList = mutableListOf<Int>()
//
//                    subwaySplit.forEachIndexed { index, s ->
//
//                        if(s.contains(station)){
//                            if(currSubWaySplitIndex != index){
//                                stationIncludeIndexList.add(index)
//                            }
//                        }
//                    }
//
//                    if(stationIncludeIndexList.isEmpty()){
//                        continue
//                    }
//                    else{
//                        stationIncludeIndexList.forEach { changedCurrPnt ->
//
//                            val newVisitedList = mutableListOf<Boolean>()
//
//                            visited.forEach {
//                                newVisitedList.add(it)
//                            }
//
//                            val newVisitedArray = newVisitedList.toBooleanArray()
//
//                            DFS(changedCurrPnt, station.toInt(), newVisitedArray, count + 1)
//                        }
//                    }
//                }
//
//            }
//
//            answerList.add(count)
//        }
//
//        DFS(currPnt, start, visited, 0)
//
//        println(answerList)
//
//        return answer
//    }
//
//}