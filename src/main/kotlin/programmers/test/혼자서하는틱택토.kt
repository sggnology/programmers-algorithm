package programmers.test

class 혼자서하는틱택토 {
    fun solution(board: Array<String>): Int {
        var solution = 1

        val emptySpotCount = getBoardEmptySpotCount(board)
        val notEmptySpotCount = 9 - emptySpotCount
        val oSpotCount = getOSpotCount(board)
        val xSpotCount = getXSpotCount(board)

        val stateOfGame = getStateOfGame(board)
        val isWinStateOccurred = stateOfGame.first
        val whoIsWinner = stateOfGame.second

        /**
         * 설명
         * - O가 선공임으로 O 보다 X 가 더 많을 수 없다.
         * */
        if(oSpotCount < xSpotCount){
            return 0
        }

        if(xSpotCount + 2 <= oSpotCount){
            return 0
        }

        if(isWinStateOccurred){
            if(whoIsWinner == 'X'){

                if(oSpotCount == xSpotCount){
                    return 1
                }
                if(xSpotCount < oSpotCount){
                    return 0
                }
            }
            if(oSpotCount == xSpotCount){
                return 0
            }
        }

        return solution
    }

    private fun getStateOfGame(board: Array<String>): Pair<Boolean, Char> {
        val winCases = arrayOf(
            arrayOf(Pair(0, 0), Pair(1, 1), Pair(2, 2)),
            arrayOf(Pair(2, 0), Pair(1, 1), Pair(0, 2)),
            arrayOf(Pair(0, 0), Pair(0, 1), Pair(0, 2)),
            arrayOf(Pair(1, 0), Pair(1, 1), Pair(1, 2)),
            arrayOf(Pair(2, 0), Pair(2, 1), Pair(2, 2)),
            arrayOf(Pair(0, 0), Pair(1, 0), Pair(2, 0)),
            arrayOf(Pair(0, 1), Pair(1, 1), Pair(2, 1)),
            arrayOf(Pair(0, 2), Pair(1, 2), Pair(2, 2)),
        )

        for (winCase in winCases) {
            if (isAllSame(board, winCase)) {
                val winner = board[winCase[0].first][winCase[0].second]
                return Pair(true, winner)
            }
        }

        return Pair(false, '.')
    }

    private fun isAllSame(board: Array<String>, indexGroup: Array<Pair<Int, Int>>): Boolean {
        return (board[indexGroup[0].first][indexGroup[0].second] != '.')
                && (board[indexGroup[0].first][indexGroup[0].second] == board[indexGroup[1].first][indexGroup[1].second])
                && (board[indexGroup[1].first][indexGroup[1].second] == board[indexGroup[2].first][indexGroup[2].second])
    }

    private fun getBoardEmptySpotCount(board: Array<String>): Int {
        var notEmptySpot = 0
        for (row in board) {
            notEmptySpot += row.count { it == '.' }
        }
        return notEmptySpot
    }

    private fun getOSpotCount(board: Array<String>): Int {
        var notEmptySpot = 0
        for (row in board) {
            notEmptySpot += row.count { it == 'O' }
        }
        return notEmptySpot
    }

    private fun getXSpotCount(board: Array<String>): Int {
        var notEmptySpot = 0
        for (row in board) {
            notEmptySpot += row.count { it == 'X' }
        }
        return notEmptySpot
    }
}