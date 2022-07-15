package programmers.highscorekit

/**
 * 설명
 * - 프로그래머스 > 고득점 kit > 완전탐색 > level2 > 카펫
 * - 경로 : https://school.programmers.co.kr/learn/courses/30/lessons/43238
 *
 * */
class Carpet {

    fun solution(brown: Int, yellow: Int): IntArray{

        /**
         * 로직설명
         * - 내부 사각형(노랑타일)의 가로길이를 w, 세로길이를 h 라고 하자.
         *
         * - #1 첫번째 조건
         * -- 2w + 2h + 4(꼭지점 타일 갯수) = brown
         *
         * - #2 두번째 조건
         * -- w*h = yellow
         *
         * */
//        2w + 2h + 4 = brown
//        w * h = yellow

        var yellowWidth = 0
        var yellowHeight = 0

        var yellowSurfaceAreaArray: IntArray? = null
        var result: IntArray? = null

        val scaledBrown = (brown - 4)/2

        val firstCondMutableList = mutableListOf<IntArray>()

        for(width in scaledBrown/2..scaledBrown){
            yellowWidth = width
            yellowHeight = scaledBrown - width

            if(yellowWidth+yellowHeight == scaledBrown){
                if(yellowHeight<=yellowWidth){
                    firstCondMutableList.add(intArrayOf(yellowWidth, yellowHeight))
                }
            }
        }

        for(firstCondEle in firstCondMutableList){
            if(firstCondEle[0] * firstCondEle[1] == yellow){
                yellowSurfaceAreaArray = firstCondEle
                break
            }
        }

        if(yellowSurfaceAreaArray == null){
            // 정답이 없다?
            return intArrayOf()
        }
        else{
            yellowWidth = yellowSurfaceAreaArray[0] + 2
            yellowHeight = yellowSurfaceAreaArray[1] + 2

            result = intArrayOf(yellowWidth, yellowHeight)

            println(result.toList())

            return intArrayOf(yellowWidth, yellowHeight)
        }
    }
}