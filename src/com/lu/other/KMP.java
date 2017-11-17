package com.lu.other;

/**
 * 
 * @author lu
 * 
 * @description kmp算法，用于查看某字符串是否是另一个字符串的子串，是则返回起始位置，否则返回-1。它的时间复杂度能达到O(m+n)，即可算作O(n)，而普通的遍历则为O(n*m)。
 *
 */
public class KMP {

    // kmp算法在遍历时，如果匹配则两个字符数组都++，不匹配的时候，以子串不匹配的位置为起始，把子串向后移动“匹配个数-当前位置的前后缀最大匹配长度”个位置。所以kmp的关键是next数组的求解。
    public static int kmpSearch(String s, String m) {
        if (s == null || m == null || s.length() < 1 || m.length() > s.length()) {
            return -1;
        }
        
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int sIndex = 0;
        int mIndex = 0;
        int[] next = getNextArr(match);
        
        // 遍历两个字符数组，相等时都++，不相等时，如果该位置的next值为-1，则说明开头就没匹配上，则移动父字符数组，否则，如果不相等且有部分匹配时，令match的位置为next值。
        while (sIndex < str.length && mIndex < match.length) {
            if (str[sIndex] == match[mIndex]) {
                sIndex++;
                mIndex++;
            } else if (next[mIndex] == -1) {
                sIndex++;
            } else {
                mIndex = next[mIndex];
            }
        }
        
        return mIndex == match.length ? sIndex - mIndex : -1;
    }
    
    // next数组的求解，即通过数组中的前一个数求得后一个数，当结尾的字符不匹配时，就不断往前查找。
    public static int[] getNextArr(char[] match) {
        if (match.length == 1) {
            return new int[] {1};
        }
        
        int next[] = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        
        // 即前缀从位置0开始，后缀从位置1开始，值相等时，该位置的next值为前缀++，否则前缀向前查找，让位置等于next[前缀]，当找到最前面时，该位置next值为0。
        while (pos < match.length) {
            if (match[pos - 1] == match[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        
        return next;
    }
    
    public static void main(String[] args) {
        String str = "abcdabce";
        String match = "bcdc";
        int index = KMP.kmpSearch(str, match);
        System.out.println(index);
    }
    
}
