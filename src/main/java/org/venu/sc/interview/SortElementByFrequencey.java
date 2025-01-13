package org.venu.sc.interview;

import java.util.*;

public class SortElementByFrequencey {

    public static void main(String[] args){
        //Declar and initialize array



        int[] freuqencyEleArray = { 2, 5, 2, 6, -1, 9999999, 5, 8, 8, 8 };

        //{4, 4, 2, 2, 2, 2, 3, 3, 1, 1, 6, 7, 5};
        Map<Integer,Integer> frquencyMap =new HashMap<>();
        List<Integer> outputArray = new ArrayList<>();
        for(int frqValue : freuqencyEleArray ){
           int count = frquencyMap.getOrDefault(frqValue,0);
            frquencyMap.put(frqValue,count+1);
            outputArray.add(frqValue);
        }
        SortComparator sortComparator= new SortComparator(frquencyMap);
        Collections.sort(outputArray,sortComparator);
        // Final Output
        for (Integer i : outputArray) {
            System.out.print(i + " ");
        }
    }


}

class SortComparator implements Comparator<Integer>{
    Map<Integer,Integer> frquencyMap;
    SortComparator(Map<Integer,Integer> sortfrquencyMap){
        this.frquencyMap=sortfrquencyMap;
    }
    @Override
    public int compare(Integer o1, Integer o2) {
       int frquecnyValue= frquencyMap.get(o2).compareTo(frquencyMap.get(o1));
        int valueCompare=    o1.compareTo(o2);
        if(frquecnyValue==0){
            return valueCompare;
        }else{
            return frquecnyValue;
        }
    }
}
