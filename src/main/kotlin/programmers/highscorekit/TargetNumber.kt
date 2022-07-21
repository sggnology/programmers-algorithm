package programmers.highscorekit

class TargetNumber {
    fun solution(numbers: IntArray, target: Int): Int{
        return DFS(numbers, target)
    }

    fun DFS(numbers: IntArray, target: Int): Int{
        val resultArray = arrayListOf<Int>()

        DFSUtil(numbers, 0, 0, target, resultArray)

        return resultArray.size
    }

    fun DFSUtil(numbers: IntArray, index: Int, result: Int, target: Int, resultArray: ArrayList<Int>){

        if(numbers.size <= index){
            if(result == target){
                resultArray.add(0)
            }
            return
        }

        DFSUtil(numbers, index + 1, result + numbers[index], target, resultArray)
        DFSUtil(numbers, index + 1, result - numbers[index], target, resultArray)

    }
}