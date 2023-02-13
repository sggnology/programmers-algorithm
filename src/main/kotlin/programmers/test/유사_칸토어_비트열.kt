package programmers.test

import java.time.Duration
import java.time.LocalDateTime

class 유사_칸토어_비트열 {

    // a : 97
    // r : 114

    fun solution(n: Int, l: Long, r: Long): Int {
        var answer: Int = 0

        println('a'.code)
        println('v'.code)
        println('z'.code)

        return answer
    }

    fun getCantoeBit(){

    }

    fun getCantoeZeroRule32Decimal(): String{
        return "00000"
    }

    fun getCantoeOneRule32Decimal(): String{
        return "rr0rr"
    }





//    fun solution(n: Int, l: Long, r: Long): Int {
//        var answer: Int = 0
//
//        var cantoeBit = getCantoeBit(n, r.toInt())
//
//        for(index in l-1 until r){
//            if(cantoeBit[index.toInt()].toString() == "1") answer++
//        }
//
//        println(answer)
//
//        return answer
//    }
//
//    fun getCantoeBit(n: Int, limitSize: Int): String{
//        val startCantoeBit = "1"
//        var result = startCantoeBit
//
//        for(i in 0 until n){
//
//            val startTime = LocalDateTime.now()
//
//            var tmpResult = ""
//
//            for(ele in result){
//                tmpResult += getPattern(ele.toString().toInt())
//                if(limitSize <= tmpResult.length){
//                    break
//                }
//            }
//            val endTime = LocalDateTime.now()
//
//            println("n: $i, 소요시간 : ${Duration.between(startTime, endTime).seconds}")
//
//            result = tmpResult
//        }
//
//        return result
//    }
//
//    fun getPattern(num: Int): String{
//
//        var result = ""
//
//        when(num){
//            0 -> {
//                result = getZeroToPattern()
//            }
//            1 -> {
//                result = getOneToPattern()
//            }
//        }
//
//        return result
//    }
//
//    fun getOneToPattern(): String{
//        return "11011"
//    }
//
//    fun getZeroToPattern(): String{
//        return "00000"
//    }
}