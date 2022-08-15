package programmers.test

class 최대공약수최소공배수 {
    fun solution(num1: Int, num2: Int): IntArray{
        var num1Tmp = num1
        var num2Tmp = num2
        var rest: Int

        var gcd: Int

        while(true){
            if(num1Tmp > num2Tmp){
                rest = num1Tmp % num2Tmp

                if(rest == 0){
                    gcd = num2Tmp
                    break
                }

                num1Tmp = num2Tmp
                num2Tmp = rest
            }
            else{
                rest = num2Tmp % num1Tmp

                if(rest == 0){
                    gcd = num1Tmp
                    break
                }

            }
        }

        var lcm = (num1*num2)/gcd

        println(intArrayOf(gcd, lcm).toList())

        return intArrayOf(gcd, lcm)
    }
}