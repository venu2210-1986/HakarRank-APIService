package org.venu.sc.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClosestNumbers {
    public static void main(String args[]){
       int[] arr = {1,3,5,7,2,4,6,8,9,10};
        List<Integer> arr1 = new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            arr1.add(arr[i]);
        }
        Collections.sort(arr1);
        System.out.println(arr1);
        int minDifference = Integer.MAX_VALUE;
        System.out.println(minDifference);
        // Find the smallest difference between consecutive elements
        for(int i=1; i<arr1.size();i++){
          int currentDifference = arr1.get(i)-arr1.get(i-1);
          //System.out.println(currentDifference);
          minDifference= Math.min(minDifference,currentDifference);
         // System.out.println(minDifference);

        }
        List<List<Integer>> resultList = new ArrayList<>();

        // Collect all pairs with the minimum difference
        for(int i=1; i<arr1.size();i++) {
            int currentDifference = arr1.get(i) - arr1.get(i - 1);
            if(currentDifference==minDifference){
                List<Integer> result = new ArrayList<>();
                result.add(arr1.get(i-1));
                result.add(arr1.get(i));//
                resultList.add(result);

            }
        }
       for(List<Integer> clostetNumber: resultList){
           System.out.println(clostetNumber);
       }
    }
}
