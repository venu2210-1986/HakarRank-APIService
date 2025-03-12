package org.venu.sc.interview;

public class CountSubArrayThreshhold {

    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        threshold *= k;
        int s = 0;
        for (int i = 0; i < k; ++i) {
            s += arr[i];
        }
        int ans = s >= threshold ? 1 : 0;
        for (int i = k; i < arr.length; ++i) {
            s += arr[i] - arr[i - k];
            ans += s >= threshold ? 1 : 0;
        }
        return ans;
    }

    public static void main(String args[]){
        int[] array ={2,2,2,2,5,5,5,8};
       int [] array1= {11,13,17,23,29,31,7,5,2,3};
        int k =3;
        int  threshHold = 5;
       int count = numOfSubarrays(array1,k,threshHold);
       System.out.println(count);
    }
}
