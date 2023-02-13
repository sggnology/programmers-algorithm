package programmers.test

class 뒤에있는큰수찾기 {
//    fun solution(numbers: IntArray): IntArray {
//
//    }

    fun solution(numbers: IntArray): IntArray {

        val solution = IntArray(numbers.size){0}
        solution[solution.size - 1] = -1

        val maxNumberInNumbers = numbers.maxOf { it }

        for(outerNumberIndex in numbers.indices){
            val targetNumber = numbers[outerNumberIndex]

            for(innerNumberIndex in outerNumberIndex + 1 until numbers.size){
                val compareNumber = numbers[innerNumberIndex]

                if(targetNumber == maxNumberInNumbers){
                    solution[outerNumberIndex] = -1
                    break
                }

                if(targetNumber < compareNumber){
                    solution[outerNumberIndex] = compareNumber
                    break
                }

                if(innerNumberIndex == numbers.size - 1){
                    solution[outerNumberIndex] = -1
                }
            }
        }

//        println(solution.toList())

        return solution
    }
}