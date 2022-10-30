package programmers.test

class 롤케이크자르기 {
    fun solution(topping: IntArray): Int {

        var result = 0

        /**
         * 설명
         * - 만약 topping 의 크기가 `1` 이라면 공평하게 나눠 먹을 수 없다.
         * */
        if(topping.size == 1){
            return 0
        }

        /**
         * 설명
         * - `철수`와 `철수 동생`이 slicingPoint 를 기준으로 몇가지 종류의 topping 을 가지고 있는지 표현한다.
         *
         * @Variable
         * - 철수 : cs
         * - 철수 동생 : csBrother
         * - slicingPoint : `철수`와 `철수 동생`이 공평하게 나눠먹을 수 있는 시점
         * */
        val csGroup = topping.slice(IntRange(0,0)).toTypedArray().groupingBy { it }.eachCount().toMutableMap()
        var csGroupKinds = csGroup.keys.size

        val csBrotherGroup = topping.slice(IntRange(1,topping.size-1)).toTypedArray().groupingBy { it }.eachCount().toMutableMap()
        var csBrotherGroupKinds = csBrotherGroup.keys.size

        var slicingPoint = 1

        /**
         * 설명
         * - csGroup 에 값이 없으면 kind 중가 및 csGroup 에 해당 topping 종류를 추가
         * - csBrotherGroup 에 값이 없어지면 kind 감소
         *
         * while 문 탈출 조건
         * - slicingPoint 를 기준으로 철수가 먹을 수 있는 종류가 더 많아지면 더 이상 공평하게 나뉘어지는 조건이 생길 수 없음
         * - 철수 동생이 먹을 수 있는 종류가 `0`이 될때
         * */
        while(true){
            if(csBrotherGroupKinds < csGroupKinds  || csBrotherGroupKinds == 0) break;
            if(csGroupKinds == csBrotherGroupKinds) result += 1

            val whatTopping = topping[slicingPoint]

            csBrotherGroup[whatTopping] = csBrotherGroup[whatTopping]!!.minus(1)

            if(csGroup[whatTopping] == null){
                /**
                 * csGroup 이 mutableMap 인 이유
                 * - 철수가 다음에 먹으려고 하는 토핑의 종류가 중복되는지 확인
                 * */
                csGroup[whatTopping] = 1
                csGroupKinds += 1
            }

            if(csBrotherGroup[whatTopping] == 0){
                csBrotherGroupKinds -= 1
            }

            slicingPoint += 1
        }

        return result
    }

    /**
     * 실패한 방법
     *
     * 설명
     * - topping 의 길이가 1,000,000 인걸 고려할 때 toSet() 을 통해 풀려고 시도하면 `시간초과` 오류가 발생한다.
     *
     * fun solution(topping: IntArray): Int {

    var result = 0

    val toppingKinds = topping.toSet().size

    val startingPoint = toppingKinds / 2;

    for(sliceIndex in startingPoint .. topping.size - startingPoint){
    val cs = topping.slice(IntRange(0, sliceIndex - 1)).toSet().size
    val csBrother = topping.slice(IntRange(sliceIndex, topping.size - 1)).toSet().size

    if(cs == csBrother){
    result += 1
    }
    }

    println("test slice : ${topping.slice(IntRange(2,topping.size-1))}")

    println("result : $result")

    return result
    }
     *
     * */
}