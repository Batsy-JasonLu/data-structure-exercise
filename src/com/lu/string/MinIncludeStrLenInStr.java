package com.lu.string;

/**
 * 
 * @author lu
 * 
 * @description 给定字符串s1和s2，求s1中包含了s2所有字符的最小子串，如s1=abcde，s2=ac，s1包含s2的子串为abc，长度为3。
 *
 */
public class MinIncludeStrLenInStr {

    // 该方法中，设置left/right变量，用来表示一个子串的边界；设置一个map数组，先变量s2并map++，然后开始操作s1，先让right不断向右，同时map--。
    // map--之后再判断，这时value还大于等于0，则match--，因为s2中的字符都至少为1，所以先--再判断，这时还大于等于0的说明是s2字符，可以match--，否则说明是第一次遇到的字符，--之后肯定为-1或更小，则不能match--。
    // 当match为0时，说明s2字符都包含了，这时开始移动left，map++并left++，如果不在s2里的字符，则-1<0，还可以++，否则0不小于0，则说明left到这里时，不能再往右了，否则不能包含所有字符了。
    // 这时right-left+1得到长度，然后match++，map++，left++，right++继续往下看在，直到末尾。
    public int getMinLen(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < str2.length()) {
            return 0;
        }
        
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        int left = 0;
        int right = 0;
        int[] map = new int[256];
        int match = c2.length;
        int minLen = Integer.MAX_VALUE;
        
        for (int i = 0; i < c2.length; i++) {
            map[c2[i]]++;
        }
        
        while (right != c1.length) {
            map[c1[right]]--;
            
            if (map[c1[right]] >= 0) {
                match--;
            }
            
            if (match == 0) {
                while (map[c1[left]] < 0) {
                    map[c1[left++]]++;
                }
                
                minLen = Math.min(minLen, right - left + 1);
                match++;
                map[c1[left++]]++;
            }
            
            right++;
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
    public static void main(String[] args) {
        String str1 = "adabbca";
        String str2 = "acb";
        MinIncludeStrLenInStr main = new MinIncludeStrLenInStr();
        int res = main.getMinLen(str1, str2);
        System.out.println(res);
    }

}
