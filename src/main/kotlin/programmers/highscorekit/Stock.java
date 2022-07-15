package programmers.highscorekit;

import java.util.Arrays;

/**
 * 설명
 * - 프로그래머스 > 고득점 kit > 스택/큐 > level2 > 주식가격
 * - 경로 : https://school.programmers.co.kr/learn/courses/30/lessons/42584
 *
 * */
public class Stock {
    public int[] solution(int[] prices) {

        int[] result;

        // 주식 데이터가 없을 수 도 있습니다.
        if (prices.length == 0) {
            result = new int[]{};
            return result;
        }

        // 주식 데이터가 하나만 있을 수 도 있습니다.
        else if (prices.length == 1) {
            result = new int[]{0};
            return result;
        }

        // 주식 데이터가 둘 이상일 경우.

        result = new int[prices.length];

        // 현재 비교하고자 하는 주식데이터에 대한 인덱스
        int curIdx = 0;

        // 모든 데이터를 비교했는지 알아보기 위한 조건
        while (curIdx < prices.length) {

            // 마지막초 데이터는 비교값이 없음으로 결과는 `0`이 될 것이다.
            // (사실 아래 조건문을 통해 while 문에서 탈출하기 때문에,
            // 위의 while 조건문은 true 로 해도 상관은 없다.
            // 단지 의도를 표현하고자 코드로 작성하였다.)
            if (curIdx == (prices.length - 1)) {
                result[curIdx] = 0;
                break;
            }

            int cmpValue = prices[curIdx];

            // idx : 현재 값보다 다음 값을 비교할 것이다.
            for (int idx = curIdx + 1; idx < prices.length; idx++) {

                // 해당 초의 주식가격이 떨어진다면
                if (prices[idx] < cmpValue) {
                    result[curIdx] = idx - curIdx;
                    curIdx += 1;
                    break;
                }

                // 주식가격이 마지막까지 떨어지지 않는다면
                if (idx == (prices.length - 1)) {
                    result[curIdx] = prices.length - (curIdx + 1);
                    curIdx += 1;
                }
            }
        }

        System.out.println(Arrays.toString(result));

        return result;
    }
}