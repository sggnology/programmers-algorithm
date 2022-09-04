package programmers.practice;

import java.util.*;

public class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();

        int tmpN = n - 1;

        while (-1 < tmpN) {
            answer.append(tmpN % 3);

            tmpN = tmpN / 3 - 1;

            if (0 <= tmpN && tmpN <= 2) {
                answer.append(tmpN);
                break;
            }
        }

        StringBuilder tmpAnswer = new StringBuilder();

        for (int index = answer.length() - 1; 0 <= index; index--) {
            tmpAnswer.append(
                converter(
                        Integer.parseInt(String.valueOf(answer.charAt(index)))
                )
            );
        }

        System.out.println(tmpAnswer);

        return tmpAnswer.toString();
    }

    String converter(int indexN) {
        switch (indexN) {
            case 0:
                return "1";
            case 1:
                return "2";
            case 2:
                return "4";
        }

        return "error";
    }
}
