package com.lu.string;

/**
 * 
 * @author lu
 * 
 * @description 给定一个字符数组，它的左半区不含空字符，但有空格字符，右半区全是空字符，且足够大，现要把左半区全部的空格都替换为%20，要求时间复杂度为o(n)，空间复杂度为o(1)，即必须在本数组里操作，不能额外使用别的数组。
 *
 */
public class StringSpaceReplace {

    public String spaceReplace(char[] ch) {
        if (ch == null) {
            return null;
        }
        
        int leftLen = 0;
        int spaceNum = 0;
        
        for (leftLen = 0; leftLen < ch.length && ch[leftLen] != 0; leftLen++) {
            if (ch[leftLen] == ' ') {
                spaceNum ++;
            }
        }
        
        int j = leftLen + 2 * spaceNum - 1;
        for (int i = leftLen - 1; i > -1; i--) {
            if (ch[i] != ' ') {
                ch[j] = ch[i];
                j--;
            } else {
                ch[j] = '0';
                j--;
                ch[j] = '2';
                j--;
                ch[j] = '%';
                j--;
            }
        }
     
        return String.valueOf(ch);
    }
    
    public static void main(String[] args) {
        char[] ch = new char[12];
        ch[0] = 'a';
        ch[1] = ' ';
        ch[2] = 'b';
        ch[3] = ' ';
        ch[4] = ' ';
        ch[5] = 'c';
        StringSpaceReplace main = new StringSpaceReplace();
        String res = main.spaceReplace(ch);
        System.out.println(res);
    }
    
}
