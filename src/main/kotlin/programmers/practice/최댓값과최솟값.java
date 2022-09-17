package programmers.practice;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 최댓값과최솟값 {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();

        IntStream minAnswerArr = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt);
        IntStream maxAnswerArr = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt);

        answer.append(minAnswerArr.min().getAsInt());
        answer.append(" ");
        answer.append(maxAnswerArr.max().getAsInt());

//        System.out.println(answer.toString());

        return answer.toString();
    }
}
