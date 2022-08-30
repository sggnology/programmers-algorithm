package programmers.practice;

import java.util.*;

public class Solution {
    public String solution(int n){
        StringBuilder answer = new StringBuilder();

        int[] arr124 = {1, 2, 4};

        Map<Integer, String> map124First = new HashMap<>();

        map124First.put(0, "1");
        map124First.put(1, "2");
        map124First.put(2, "4");

        int tmpN = n;

        List<Integer> tmpList = new ArrayList<>();
        List<Integer> reverseTmpList = new ArrayList<>();

        while(tmpN != 0){
            tmpList.add(tmpN % 3);
            tmpN = tmpN / 3;
        }

        for(int i = tmpList.size() - 1; 0 <= i; i--){
//            reverseTmpList.add(tmpList.get(i));
            answer.append(tmpList.get(i));
        }



        for(int i = 0; i<answer.length() - 1; i++){
            if(answer.charAt(i) == '1'){
                if(i+1 < answer.length()){
                    if(answer.charAt(i+1) == '0'){
                        answer = answer.replace(i, i+2, "4");
                    }
                }
            }
        }

        for(int i = 0; i<answer.length(); i++){
            if(answer.charAt(i) == '0'){
                answer = answer.replace(i, i+1, "1");
            }
            else if(answer.charAt(i) == '1'){
                answer = answer.replace(i, i+1, "2");
            }
        }



        System.out.println(answer);



        for (int i=0;i<tmpList.size();i++) {
            answer.append(map124First.get(tmpList.get(i)));
        }

        System.out.println(answer);

        return answer.toString();
    }

//    public String solution(int n){
//        StringBuilder answer = new StringBuilder();
//
//        Map<String, String> map124 = new HashMap<>();
//
//        map124.put("1", "1");
//        map124.put("2", "2");
//        map124.put("3", "4");
//        map124.put("4", "11");
//        map124.put("5", "12");
//        map124.put("6", "14");
//        map124.put("7", "21");
//        map124.put("8", "22");
//        map124.put("9", "24");
//        map124.put("10", "41");
//
//        String nString = n+"";
//        for(int i=0;i<nString.length();i++){
//            String[] arrNString = nString.split("");
//
//
//            String tmpTenCheck;
//
//            if(i == nString.length() - 1){
//                tmpTenCheck = "";
//            }
//            else{
//                tmpTenCheck = arrNString[i]+arrNString[i+1];
//            }
//
//            if(tmpTenCheck.equals("10")){
//                answer.append(map124.get("10"));
//            }
//            else{
//                answer.append(map124.get(arrNString[i]));
//            }
//        }
//
//        // System.out.println(answer);
//
//        return answer.toString();
//    }
}
