package com.lu.string;

/**
 * 
 * @author lu
 * 
 * @description 判断括号字符串的有效性和最长有效长度。如())返回false，()()为true，求长度时，()(()()(返回4。
 *
 */
public class BracketString {

    // 判断直接看(和)的数量是否相等即可。
    public boolean isBracketStr(String str) {
        if (str == null) {
            return false;
        }
        
        char[] c = str.toCharArray();
        int count = 0;
        
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(') {
                count ++;
            } else if (c[i] == ')') {
                count --;
            } else {
                return false;
            }
        }
        
        if (count == 0) {
            return true;
        }
        
        return false;
    }
    
    // 求有效字符串的最大长度，要用动态规划，dp[i]遇到)时，去找最前面配对的(，然后配对完再看前面有没有完整的()，有要再加上。
    public int getMaxLen(String str) {
        if (str == null) {
            return 0;
        }
        
        char[] c = str.toCharArray();
        int[] dp = new int[c.length];
        int pre = 0;
        int res = 0;
        
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ')') {
                pre = i - dp[i - 1] - 1;
                
                if (pre >= 0 && c[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            
            res = Math.max(res, dp[i]);
        }
        
        return res;
    }

    public static void main(String[] args) {
        String str = "(()())()";
        BracketString main = new BracketString();
        boolean res = main.isBracketStr(str);
        System.out.println(res);
        int sum = main.getMaxLen(str);
        System.out.println(sum);
    }

}
