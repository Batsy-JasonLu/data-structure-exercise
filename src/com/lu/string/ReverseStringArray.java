package com.lu.string;

/**
 * 
 * @author lu
 * 
 * @description 翻转字符串，如pig will fly，翻转为fly will pig。要求时间复杂度为o(n)，空间复杂度为o(1)。补充问题：给一个字符数组和整数，把整数大小的左半区移到右半区，如abcde,3，返回deabc。
 *
 */
public class ReverseStringArray {

    // 该方法是先把整个字符串翻转，如pig will fly翻转为ylf lliw gip，然后在依次把每次单词翻转即可。
    public String reverseStrArr(String str) {
        if (str == null) {
            return null;
        }
        
        char[] c = str.toCharArray();
        reverse(c, 0, c.length - 1);
        
        // 用这个遍历方法，一定要考虑最后一个字符不会被操作到的情况，所以一定要再加上单独对最后一项的操作。
        int start = -1;
        int count = 0;
        char cur = 0;
        for (int i = 0; i < c.length; i++) {
            cur = c[i];
            if (cur != ' ') {
                start = start == -1 ? i : start;
                count ++;
                
            } else {
                reverse(c, start, start + count - 1);
                
                start = -1;
                count = 0;
            }
        }
        
        reverse(c, start, start + count - 1);
        
        return String.valueOf(c);
    }
    
    // 翻转整个字符串的方法，即不断交换数组最开头和最末尾的字符，需要记住！
    public void reverse(char[] c, int start, int end) {
        char tmp = 0;
        
        while (start < end) {
            tmp = c[start];
            c[start] = c[end];
            c[end] = tmp;
            start ++;
            end --;
        }
    }
    
    public static void main(String[] args) {
        String string = "pig will fly";
        ReverseStringArray main = new ReverseStringArray();
        String res = main.reverseStrArr(string);
        System.out.println(res);
    }

}
