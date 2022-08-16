package programmers.kakao2021채용연계형인턴십

/**
 * 설명
 * - 프로그래머스 > 2021 카카오 채용연계형 인턴십
 * - 경로 : https://school.programmers.co.kr/learn/courses/30/lessons/81302
 *
 * */
class 거리두기확인하기 {
    fun solution(places: Array<Array<String>>): IntArray{

        /**
         * 로직설명
         * - 코테설명 이미지를 보고 힌트를 얻었다.
         * - 이미지에 따르면 패턴이 존재한다는 것을 알 수 있다.
         * - 해당 패턴은 예를들면 다음과 같다. "PP", "POP"
         * */

        var answer: IntArray = IntArray(places.size){0}

        places.forEachIndexed { index, place ->
            var colPlace = generateColPlaces(place)
            var diagonalPlace = generateDiagonalPlaces(place)

            if(casePPORPOP(place) || casePPORPOP(colPlace) || caseDiagonal(diagonalPlace)){
                answer[index] = 0
            }
            else{
                answer[index] = 1
            }
        }

        print(answer.toList())

        return answer
    }

    /**
     * 설명
     * - 면접장의 열을 나타내는 string 배열입니다.
     * */
    fun generateColPlaces(place: Array<String>): Array<String>{
        val colPlaces = mutableListOf<String>()

        for(i in place.indices){

            var tmpString = ""

            for(rowPlace in place){
                tmpString += rowPlace[i]
            }

            colPlaces.add(tmpString)
        }

        return colPlaces.toTypedArray()
    }

    /**
     * 설명
     * - 면접장에서 나올수 있는 2*2 사각형의 모든 경우에 수 에 대한 string 배열입니다.
     * - 0,1 인덱스는 2*2 사각형의 첫번째 행을 의미합니다.
     * - 1,2 인덱스는 2*2 사각형의 두번째 행을 의미합니다.
     * */
    fun generateDiagonalPlaces(place: Array<String>): Array<String>{
        val diagonalPlaces = mutableListOf<String>()

        val size = (place.size - 1)*(place.size - 1)
        
        var xCoordinate = 0
        var yCoordinate = 0

        var startingPoint = 0;

        var tmpString = ""
        
        for(i in 0 until size){
            xCoordinate = i%4
            yCoordinate = i/4

            tmpString = "" +
                    "${place[xCoordinate][yCoordinate]}" +
                    "${place[xCoordinate][yCoordinate+1]}" +
                    "${place[xCoordinate+1][yCoordinate]}" +
                    "${place[xCoordinate+1][yCoordinate+1]}"

            diagonalPlaces.add(tmpString)
        }

        return diagonalPlaces.toTypedArray()
    }

    /**
     * 설명
     * - PP, POP 패턴일 경우 거리두기를 지키지 않은 것입니다.
     * */
    fun casePPORPOP(place: Array<String>): Boolean{
        val casePP = "PP"
        val casePOP = "POP"

        for(placeString in place){
            if(placeString.contains(casePP)){
                return true
            }
            if(placeString.contains(casePOP)){
                return true
            }
        }

        return false
    }

    /**
     * 설명
     * - 아래의 case1~case6 까지의 경우 2*2 사각형 에서 경우를 지키지 않은 경우입니다.
     * */
    fun caseDiagonal(place: Array<String>): Boolean{
        val case1 = "POOP"
        val case2 = "POXP"
        val case3 = "PXOP"
        val case4 = "OPPO"
        val case5 = "OPPX"
        val case6 = "XPPO"

        for(diagonalPlace in place){
            if(diagonalPlace.contains(case1) ||
                diagonalPlace.contains(case2) ||
                diagonalPlace.contains(case3) ||
                diagonalPlace.contains(case4) ||
                diagonalPlace.contains(case5) ||
                diagonalPlace.contains(case6)){
                return true
            }
        }

        return false
    }
}