package programmers.highscorekit;


import javax.xml.soap.Node;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 설명
 * - 프로그래머스 > 고득점 kit > 깊이/너비 우선 탐색(DFS/BFS) > level2 > 게임 맵 최단거리
 * - 경로 : https://school.programmers.co.kr/learn/courses/30/lessons/1844?language=java
 *
 * */
public class ShortestWayInGameMap {

    /**
     * BFS 풀이
     * - 너비 우선탐색은 웬만하면 Queue 를 사용해서 푸는게 좋다.(법칙 같은?)
     * - 미로문제는 대부분 유형이 비슷하다.
     * - 플레이어는 대각선으로 움직이지 못하기때문에 움직임에 대한 조건이 단순하다.(좌, 우, 상, 하)
     *
     * 특이사항
     * - 제일 최단 거리를 구하는 것이기 때문에 제일 먼저 조건에 맞게 return 되는 값이 정답이다.
     * */
    public int solution(int[][] maps){
        int rows = maps.length;
        int columns = maps[0].length;

        int[][] visited = new int[rows][columns];

        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{0,0,1});

        while(0 < queue.size()){
            int[] queueOutput = queue.poll();

            int row = queueOutput[0];
            int column = queueOutput[1];
            int resultCnt = queueOutput[2];

            if(row == rows - 1 && column == columns - 1){
                return resultCnt;
            }

            if(row + 1 < rows){
                if(maps[row+1][column] == 1 && visited[row+1][column] == 0){
                    visited[row+1][column] = 1;
                    queue.add(new int[]{row+1, column, resultCnt + 1});
                }
            }

            if(column + 1 < columns){
                if(maps[row][column+1] == 1 && visited[row][column+1] == 0){
                    visited[row][column+1] = 1;
                    queue.add(new int[]{row, column+1, resultCnt + 1});
                }
            }

            if(0 <= row - 1){
                if(maps[row-1][column] == 1 && visited[row-1][column] == 0){
                    visited[row-1][column] = 1;
                    queue.add(new int[]{row-1, column, resultCnt + 1});
                }
            }

            if(0 <= column - 1){
                if(maps[row][column-1] == 1 && visited[row][column-1] == 0){
                    visited[row][column-1] = 1;
                    queue.add(new int[]{row, column-1, resultCnt + 1});
                }
            }
        }

        return -1;
    }

    /**
     * DFS 풀이(틀림)
     * - maps[0,0] 에서 시작해서 위, 왼쪽, 아래, 오른쪽중 하나를 고르는데 이때 값이 0인 값으로는 갈 수 없다.
     * - maps[0,1] 의 값이 1이라고 할때 위와같이 반복하는데 이때 `방문했던 곳`을 기억하여 가지 못하게끔 한다.
     * - 이렇게 목표지점에 도달했을때 값들중 최소값을 return 한다.
     * - 만약 목표지점에 도달하지 못하면 `-1`을 return 한다.
     *
     * - 특이사항1 (목표지점에 도달하지 못하는 조건)
     * -- 다음 방법을 고르려 할때 도달하지 이미 모두 방문한 곳이라면 `-1`을 내뱉게끔 한다.
     * -- 모든 값이 `-1`이라면 목표지점에 도달하지 못한 것이다.
     *
     */
    public int solution_dfs(int[][] maps){

        ArrayList<Integer> resultArr = new ArrayList<Integer>();

        int rows = maps.length;
        int columns = maps[0].length;

        DFS(rows, columns, maps, resultArr);

        if(resultArr.isEmpty()){
            return -1;
        }

        return resultArr.get(0);
    }

    public void DFS(int rows, int columns, int[][] maps, ArrayList<Integer> resultArr){
        int[][] visited = new int[rows][columns];

        DFSUtil(rows, columns, maps, 0, 0, visited, 0, resultArr);
    }

    public void DFSUtil(int rows, int columns, int[][] maps, int row, int column, int[][] visited, int count, ArrayList<Integer> resultArr){

        if(row == rows-1 && column == columns - 1){
            resultArr.add(count+1);
        }

        if(maps[0][0] == 0){
            return;
        }

        if(rows <= row || row < 0 || columns <= column || column < 0){
            return;
        }

        if(visited[row][column] == 0){
            if(maps[row][column] == 1){

                int[][] visitedTmp = new int[rows][columns];

                visited[row][column] = 1;

                for(int i = 0; i<rows; i++){
                    System.arraycopy(visited[i], 0, visitedTmp[i], 0, columns);
                }

                DFSUtil(rows, columns, maps, row + 1, column, visitedTmp, count + 1, resultArr);
                DFSUtil(rows, columns, maps, row, column + 1, visitedTmp, count + 1, resultArr);
                DFSUtil(rows, columns, maps, row - 1, column, visitedTmp, count + 1, resultArr);
                DFSUtil(rows, columns, maps, row, column - 1, visitedTmp, count + 1, resultArr);
            }
        }
    }
}