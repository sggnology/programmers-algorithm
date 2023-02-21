package programmers.test

class 무인도여행 {
    fun solution(maps: Array<String>): IntArray {
        val answer = mutableListOf<Int>()

        val width = maps[0].length
        val height = maps.size

        val visited = Array(height) { IntArray(width) { 0 } }

        lateinit var twoDimensionTargetIndex: Pair<Int, Int>

        /**
         * 설명
         * - 지도의 모든 부분을 탐색한다.
         *
         * 동작예시
         * - 조건1 : 방문하지 않은 곳
         * - 조건2 : 섬인곳
         * - 위 두 조건을 만족할때 dfs 를 통해 인접한 섬에 대한 음식의 갯수를 추출한다.
         * */
        for (rowIndex in 0 until height) {
            for (columnIndex in 0 until width) {

                twoDimensionTargetIndex = Pair(rowIndex, columnIndex)

                if (!isVisited(visited, twoDimensionTargetIndex)) {
                    if (isIsland(maps, twoDimensionTargetIndex)) {
                        val sumOfFoods = dfs(width, height, maps, visited, twoDimensionTargetIndex)
                        answer.add(sumOfFoods)
                    }
                }
            }
        }

        if (answer.isEmpty()) {
            return intArrayOf(-1)
        }

        return answer.toIntArray().sortedArray()
    }

    /**
     * 설명
     * - 방문한 섬에 대한 개별적인 값을 가져야 하기 때문에 dfs, dfslogic 분리
     * -- 개별적인 값 : 인접한 섬마다의 모든 음식 갯수에 대한 합
     * */
    fun dfs(
        width: Int,
        height: Int,
        maps: Array<String>,
        visited: Array<IntArray>,
        dimensionIndexGroup: Pair<Int, Int>
    ): Int {
        val accumulatedFoods = mutableListOf<Int>()
        dfsLogic(width, height, maps, visited, dimensionIndexGroup, accumulatedFoods)
        return accumulatedFoods.sum()
    }

    /**
     * 설명
     * - 방문한 섬을 기준으로 탐색 가능한 모든 섬을 확인하며 음식을 기록해둔다.
     * */
    fun dfsLogic(
        width: Int,
        height: Int,
        maps: Array<String>,
        visited: Array<IntArray>,
        dimensionIndexGroup: Pair<Int, Int>,
        accumulatedFoods: MutableList<Int>
    ) {

        if (isVisited(visited, dimensionIndexGroup)) {
            return
        } else {
            if (isIsland(maps, dimensionIndexGroup)) {
                accumulatedFoods.add(getIslandFood(maps, dimensionIndexGroup))
            }

            setVisited(visited, dimensionIndexGroup)

            val canGoCoordinates = getCanGoFromFourWayColumnIndexes(width, height, maps, dimensionIndexGroup)

            for (canGoCoordinate in canGoCoordinates) {
                dfsLogic(width, height, maps, visited, canGoCoordinate, accumulatedFoods)
            }
        }
    }

    /**
     * 설명
     * - 현재 탐색중인 섬을 기준으로 `오른쪽`, `왼쪽`, `위`, `아래` 을 추가적으로 확인
     * - 탐색 가능한 곳이라면 `canGoFourWayColumnIndexes` 에 탐색을 위해 추가
     *
     * 예시
     * - `오른쪽` 을 예시로 들때
     * -- 방문한 곳인지?
     * -- 섬인지?
     * */
    fun getCanGoFromFourWayColumnIndexes(
        width: Int,
        height: Int,
        maps: Array<String>,
        dimensionIndexGroup: Pair<Int, Int>
    ): Array<Pair<Int, Int>> {
        val canGoFourWayColumnIndexes = mutableListOf<Pair<Int, Int>>()

        val rowIndex = dimensionIndexGroup.first
        val columnIndex = dimensionIndexGroup.second

        val right = Pair(rowIndex + 1, columnIndex)
        val left = Pair(rowIndex - 1, columnIndex)
        val top = Pair(rowIndex, columnIndex - 1)
        val bottom = Pair(rowIndex, columnIndex + 1)

        if (isCanGo(width, height, right)) {
            if (isIsland(maps, right)) {
                canGoFourWayColumnIndexes.add(right)
            }
        }
        if (isCanGo(width, height, left)) {
            if (isIsland(maps, left)) {
                canGoFourWayColumnIndexes.add(left)
            }
        }
        if (isCanGo(width, height, top)) {
            if (isIsland(maps, top)) {
                canGoFourWayColumnIndexes.add(top)
            }
        }
        if (isCanGo(width, height, bottom)) {
            if (isIsland(maps, bottom)) {
                canGoFourWayColumnIndexes.add(bottom)
            }
        }

        return canGoFourWayColumnIndexes.toTypedArray()
    }

    /**
     * 설명
     * - IndexOutOfBounds 에러가 발생하지 않는 탐색 가능한 indexGroup 을 판별
     * */
    fun isCanGo(width: Int, height: Int, dimensionIndexGroup: Pair<Int, Int>): Boolean {

        val rowIndex = dimensionIndexGroup.first
        val columnIndex = dimensionIndexGroup.second

        if (rowIndex < 0 || height <= rowIndex) {
            return false
        }

        if (columnIndex < 0 || width <= columnIndex) {
            return false
        }

        return true
    }

    fun isVisited(visited: Array<IntArray>, dimensionIndexGroup: Pair<Int, Int>): Boolean {

        val rowIndex = dimensionIndexGroup.first
        val columnIndex = dimensionIndexGroup.second

        return visited[rowIndex][columnIndex] == 1
    }

    /**
     * 설명
     * - 현재 위치를 방문한곳으로 setting
     * */
    fun setVisited(visited: Array<IntArray>, dimensionIndexGroup: Pair<Int, Int>) {
        val rowIndex = dimensionIndexGroup.first
        val columnIndex = dimensionIndexGroup.second

        visited[rowIndex][columnIndex] = 1
    }

    fun isIsland(maps: Array<String>, dimensionIndexGroup: Pair<Int, Int>): Boolean {

        val rowIndex = dimensionIndexGroup.first
        val columnIndex = dimensionIndexGroup.second

        return maps[rowIndex][columnIndex] != 'X'
    }

    /**
     * 설명
     * - 현재 위치 섬의 음식 갯수를 추출
     * */
    fun getIslandFood(maps: Array<String>, dimensionIndexGroup: Pair<Int, Int>): Int {
        val rowIndex = dimensionIndexGroup.first
        val columnIndex = dimensionIndexGroup.second

        return maps[rowIndex][columnIndex].digitToInt()
    }
}