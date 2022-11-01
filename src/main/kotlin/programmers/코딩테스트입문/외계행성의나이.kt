package programmers.코딩테스트입문

class 외계행성의나이 {
    fun solution(age: Int): String{
        return age
            .toString()
            .split("")
            .filterNot { it.isEmpty() }
            .map { it.toInt() }
            .map { (it+97).toChar()}
            .joinToString("")
    }
}