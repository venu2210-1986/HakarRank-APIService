package org.venu.sc.interview;

public class KSmallestSubString {
    public static void main(String[] args){
        String output = shortestBeautifulSubstring("1011",1);
        System.out.println(output);
    }
    public static String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String ans = "";
        for (int i = 0; i < n; ++i) {
            for (int j = i + k; j <= n; ++j) {
                String t = s.substring(i, j);
                int cnt = 0;
                for (char c : t.toCharArray()) {
                    cnt += c - '0';
                }
                if (cnt == k
                        && ("".equals(ans) || j - i < ans.length()
                        || (j - i == ans.length() && t.compareTo(ans) < 0))) {
                    ans = t;
                }
            }
        }
        return ans;
    }
}
