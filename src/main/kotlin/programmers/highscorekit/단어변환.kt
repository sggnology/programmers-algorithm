package programmers.highscorekit

/**
 * 설명
 * - 프로그래머스 > 고득점 kit > 깊이/너비 우선탐색 > level3 > 단어변환
 * - 경로 : https://school.programmers.co.kr/learn/courses/30/lessons/43163
 * */
class 단어변환 {
    fun solution(begin: String, target: String, words: Array<String>): Int {
        val answerList = mutableListOf<Int>()

        /**
         * 설명
         * - target 이 words 에 포함되지 않는다면 해결방법은 없을것이다.
         * */
        if(words.contains(target).not()){
            return 0
        }

        val visited = BooleanArray(words.size){false}

        DFS(begin, target, answerList, words, visited, 0)

        return answerList.minOf { it }
    }

    private fun DFS(cmpValue: String, target: String, answerList: MutableList<Int>, words: Array<String>, visited: BooleanArray, count: Int){

        val copiedVisited = mutableListOf<Boolean>()
        copiedVisited.addAll(visited.toList())

//        if(getCheckWords(words, visited).isEmpty()) return
        /**
         * 설명
         * - 더이상 방문할 수 있지 않다면 재귀를 멈춘다.
         * */
        if(visited.none{ it.not() }) return

        /**
         * 설명
         * - 방문하지 않은 word 가 있다면
         * - 그 word 가 cmpValue 와 하나의 character 만 다른 값이라면
         * - 이때 target 과 바뀌고자 하는 값(word) 가 같다면, answerList 에 횟수를 추가하고 재귀를 멈춘다.
         * - 이때 .. 다르다면, 재귀를 발생시킨다.
         * */
        for((wordIndex, word) in words.withIndex()){
            if(visited[wordIndex].not()){
                if(isSingleDifferentBetweenValues(cmpValue, word)){
                    val newCount = count + 1
                    copiedVisited[wordIndex] = true

                    if(word == target) {
                        answerList.add(newCount)
                        return
                    }
                    else{
                        DFS(word, target, answerList, words, copiedVisited.toBooleanArray(), newCount)
                    }
                }
            }
        }
    }

    /**
     * 설명
     * - var1, var2 두개의 단어가 한개만 다른 단어일 경우를 확인하는 함수
     * */
    private fun isSingleDifferentBetweenValues(var1: String, var2: String): Boolean{
        var count = 0
        for(index in var1.indices){
            if(var1[index] != var2[index]) count++

            if(1 < count) return false
        }

        return count == 1
    }

    /**
     * 설명
     * - 밑의 2개의 함수는 더이상 바뀔 수 있는 경우의 수가 없을 경우를 대비해서 작성하였지만 사용하지 않고도 해결되었다.
     * */

    /**
     * 설명
     * - 방문하지 않은 words 중에 cmpValue 가 변화하면 도달할 수 있는 값이 있는지를 확인하는 함수
     * */
    private fun isThereChangeAbleValueExist(cmpValue: String, words: Array<String>, visited: BooleanArray): Boolean{
        val checkWords = getCheckWords(words, visited)

        return checkWords.map { isSingleDifferentBetweenValues(cmpValue, it) }.none { it }.not()
    }

    /**
     * 설명
     * - words 에서 방문한(visited) 경우를 제외한 Array 를 반환하는 함수
     * */
    private fun getCheckWords(words: Array<String>, visited: BooleanArray): Array<String>{
        return words.filterIndexed{ index, _ -> visited[index].not()}.toTypedArray()
    }
}