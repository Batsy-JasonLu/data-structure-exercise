package com.lu.string;

public class AddLeastStrBuildPalindrome {

    public String buildPalindrome(String str) {
        if (str == null) {
            return str;
        }
        
        char[] c = str.toCharArray();
        int[][] dp = getDp(c);
        char[] res = new char[c.length + dp[0][c.length - 1]];
        
        int i = 0;
        int j = c.length - 1;
        int resl = 0;
        int resr = c.length - 1;
        
        while (i <= j) {
            if (c[i] == c[j]) {
                res[resl++] = c[i++];
                res[resr--] = c[j--];
            } else if (dp[i][j - 1] < dp[i + 1][j]) {
                res[resl++] = c[j];
                res[resr--] = c[j--];
            } else {
                res[resl++] = c[i];
                res[resr--] = c[i++];
            }
        }
        
        return String.valueOf(res);
    }
    
    public int[][] getDp(char[] c) {
        int[][] dp = new int[c.length][c.length];
        
        for (int j = 1; j < dp.length; j++) {
            dp[j - 1][j] = c[j - 1] == c[j] ? 0 : 1;
            for (int i = j - 2; i > -1; i--) {
                if (c[i] == c[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        
        return dp;
    }
    
    public static void main(String[] args) {
        String str = "ab";
        AddLeastStrBuildPalindrome main = new AddLeastStrBuildPalindrome();
        String res = main.buildPalindrome(str);
        System.out.println(res);
    }

}
