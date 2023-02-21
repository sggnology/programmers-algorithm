package programmers.test

class 뒤에있는큰수찾기 {
    fun solution(numbers: IntArray): IntArray {

        val resultList = mutableListOf<Int>()

        val rootNode = Node()
        rootNode.numberValue = numbers.first()
        rootNode.count = 1

        buildNode(rootNode, numbers)

        var checkNode = rootNode

        while(!(checkNode.left == null && checkNode.right == null)){
            if(checkNode.left != null){

            }
        }

        return intArrayOf()
    }

    private fun buildNode(rootNode: Node, numbers: IntArray){

        var currentNode = rootNode

        for(numberIndex in 1 until numbers.size){
            val targetNumber = numbers[numberIndex]

            val newNode = Node()

            newNode.numberValue = targetNumber
            newNode.count += 1

            if(currentNode.numberValue < targetNumber){
                currentNode.right = newNode
                currentNode = currentNode.right!!
            }
            else if(currentNode.numberValue == targetNumber){
                currentNode.count += 1
            }
            else{
                currentNode.left = newNode
                currentNode = currentNode.left!!
            }
        }
    }

    class Node {
        var numberValue = 0
        var count = 0

        var left: Node? = null
        var right: Node? = null
    }

//    fun solution(numbers: IntArray): IntArray {
//
//        val solution = IntArray(numbers.size){0}
//        solution[solution.size - 1] = -1
//
//        val maxNumberInNumbers = numbers.maxOf { it }
//
//        for(outerNumberIndex in numbers.indices){
//            val targetNumber = numbers[outerNumberIndex]
//
//            for(innerNumberIndex in outerNumberIndex + 1 until numbers.size){
//                val compareNumber = numbers[innerNumberIndex]
//
//                if(targetNumber == maxNumberInNumbers){
//                    solution[outerNumberIndex] = -1
//                    break
//                }
//
//                if(targetNumber < compareNumber){
//                    solution[outerNumberIndex] = compareNumber
//                    break
//                }
//
//                if(innerNumberIndex == numbers.size - 1){
//                    solution[outerNumberIndex] = -1
//                }
//            }
//        }
//
////        println(solution.toList())
//
//        return solution
//    }
}