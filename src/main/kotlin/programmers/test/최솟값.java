package programmers.test;

import java.util.Arrays;

/**
 * 설명
 * - 프로그래머스 > 연습문제 > 최솟값
 * - 경로 : https://school.programmers.co.kr/learn/courses/30/lessons/12941?language=java
 *
 * */

public class 최솟값 {
    /**
     * 설명
     * - 제일 큰 수와 제일 작은 수의 곱의 합이 제일 작은 값일 거라는 생각으로 시작
     * */
    public int solution(int []A, int []B)
    {
        int[] sortedArrA = Arrays.stream(A).sorted().toArray();
        int[] sortedArrB = Arrays.stream(B).sorted().toArray();

        int sum = 0;

        for(int i=0;i<sortedArrA.length; i++){
            /**
             * 설명
             * - 한 배열의 제일 작은값 : sortedArrA[i]
             * - 한 배열의 제일 큰 값 : sortedArrB[sortedArrB.length - 1 - i]
             * */
            sum += sortedArrA[i]* sortedArrB[sortedArrB.length - 1 - i];
        }

        System.out.println(sum);

        return sum;
    }
}
