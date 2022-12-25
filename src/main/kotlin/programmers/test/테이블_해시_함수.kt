package programmers.test

class 테이블_해시_함수 {
    fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {

        val sortedTuples = getSortedTuples(data, col, row_begin, row_end)
        val modValues = getModValues(sortedTuples, row_begin)
        val xorOperatedValue = getXorOperatedValue(modValues)

        println(xorOperatedValue)

        return xorOperatedValue
    }

    /**
     * 설명
     * - 문제의 규칙에 따라 튜플을 정렬한다.
     * - row_begin, row_end 까지 배열을 잘라낸다.
     * */
    private fun getSortedTuples(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): List<List<Int>>{
        return data.sortedBy { it[col - 1] }
            .map { it.toList() }
            .groupBy { it[col - 1] }
            .values
            .map {values ->
                values.sortedByDescending {value -> value[0] }
            }
            .flatten()
            .slice(IntRange(row_begin-1,row_end-1))
    }

    /**
     * 설명
     * - 문제에서 `S_i` 방식의 문제를 해결하기 위한 함수
     * */
    private fun getModValues(lists: List<List<Int>>, begin: Int): List<Int>{

        var tmpBegin = begin

        val modValues = lists.map { list ->
            var sum = 0;

            for(ele in list){
                sum += ele % tmpBegin
            }

            tmpBegin += 1

            sum
        }

        return modValues
    }

    /**
     * 설명
     * - list 내부의 모든값을 `xor` 처리하는 함수
     * */
    private fun getXorOperatedValue(list: List<Int>): Int{

        val startValue = list.first()
        var xorValue = startValue;

        for(index in 1 until list.size){
            xorValue = xorValue xor list[index]
        }

        return xorValue
    }
}