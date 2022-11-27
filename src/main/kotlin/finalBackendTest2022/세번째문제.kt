package finalBackendTest2022

import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*
import kotlin.collections.HashMap

class 세번째문제 {
    fun solution(subway: Array<String>, start: Int, end: Int): Int {
        var answer: Int = 0

        val subwaySplit = subway.map { it.split(" ") }

        var currPnt = 0

        for((index, s) in subwaySplit.withIndex()){
            if(s.contains("" + start)){
                currPnt = index
                break
            }
        }

        val answerList = mutableListOf<Int>()

        val visitedMap = mutableMapOf<String, Boolean>()

        val visited = subwaySplit.flatten().toSet()

        visited.forEach{ visit ->
            visitedMap[visit] = false
        }

        fun DFS(currSubWaySplitIndex: Int, throughStation: String, visited: MutableMap<String, Boolean>, count: Int){

            visited[throughStation] = true

            for(station in subwaySplit[currSubWaySplitIndex]){

                if(Objects.equals(station, end.toString())){
                    answerList.add(count)
                    return
                }

                if(visited[station]!!){
                    continue
                }
                else{

                    val stationIncludeIndexList = mutableListOf<Int>()

                    subwaySplit.forEachIndexed { index, s ->

                        if(s.contains(station)){
                            if(currSubWaySplitIndex != index){
                                stationIncludeIndexList.add(index)
                            }
                        }
                    }

                    if(stationIncludeIndexList.isEmpty()){
                        continue
                    }
                    else{
                        stationIncludeIndexList.forEach { changedCurrPnt ->

                            val newVisitedMap = HashMap<String, Boolean>(visited).toMutableMap()

                            DFS(changedCurrPnt, station, newVisitedMap, count + 1)
                        }
                    }
                }

            }

        }

        DFS(currPnt, start.toString(), visitedMap, 0)

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