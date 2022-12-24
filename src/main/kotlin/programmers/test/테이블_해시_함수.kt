package programmers.test

class 테이블_해시_함수 {
    fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {

        val sortedTuples = getSortedTuples(data, col, row_begin, row_end)
        val modValues = getModValues(sortedTuples, row_begin)
        val xorOperatedValue = getXorOperatedValue(modValues)

        println(xorOperatedValue)

        return xorOperatedValue
    }

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

    private fun getXorOperatedValue(list: List<Int>): Int{

        val startValue = list.first()
        var xorValue = startValue;

        for(index in 1 until list.size){
            xorValue = xorValue xor list[index]
        }

        return xorValue
    }
}