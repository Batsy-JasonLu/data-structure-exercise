package com.lu.string;

/**
 * 
 * @author lu
 * 
 * @description 找到字符串的最长无重复字符子串，如str=aabcb，最长无重复字符串为abc，返回3。
 *
 */
public class SearchLongestNonRepeatStrInStr {

    // 该方法是用map记录某字符最近一次出现的位置，而pre记录最长无重复子串的开始位置的前一位，i-cur为当前的最大无重复子串长度。
    public int getNonRepeatLen(String str) {
        if (str == null) {
            return 0;
        }
        
        char[] c = str.toCharArray();
        int[] map = new int[256];
        int len = 0;
        int pre = -1;
        int cur = 0;
        
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        
        for (int j = 0; j < c.length; j++) {
            pre = Math.max(pre, map[c[j]]);
            cur = j - pre;
            len = Math.max(len, cur);
            map[c[j]] = j;
        }
        
        return len;
    }
    
    public static void main(String[] args) {
        String str = "aabcb";
        SearchLongestNonRepeatStrInStr main = new SearchLongestNonRepeatStrInStr();
        int res = main.getNonRepeatLen(str);
        System.out.println(res);
    }

}
