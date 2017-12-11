package com.lu.string;

import com.lu.other.KMP;

/**
 * 
 * @author lu
 * 
 * @description 替换字符串中连续的指定字符串。进阶：多个连续的字符串只保留一个替换值，如123abcabc为123X。
 *
 */
public class ReplaceStrInStr {

    // 本方法与原书不同，本方法用kmp作为辅助，完成了连续子串的替换，但是要让连续子串只保留一个，使用了原书的一个替换思想，即想把重复的子串都先赋为0，然后变量该字符数组，把连续的0都只用一个替换值替换。
    public String replace(String str, String from, String to) {
        if (str == null || from == null || to == null) {
            return str;
        }
        
        char[] c = str.toCharArray();
        char[] t = {0};
        int index = 0;
        int start = 0;
        String res = str;
        
        index = KMP.kmpSearch(str, from);
        
        while (index != -1) {
            res = charArrToStr(c, start, index, index + from.length(), t);
            index = KMP.kmpSearch(res, from);
            c = res.toCharArray();
        }
        
        char[] ch = res.toCharArray();
        
        return removeRepetition(ch, to);
    }
    
    public String charArrToStr(char[] c, int start, int end, int start2, char[] t) {
        int len = c.length - (start2 - end) + t.length;
        char[] ch = new char[len];
        int index = 0;
        for (int i = start; i < end; i++) {
            ch[index] = c[i];
            index ++;
        }
        
        for (int j = 0; j < t.length; j++) {
            ch[index] = t[j];
            index ++;
        }
        
        for (int k = start2; k < c.length; k++) {
            ch[index] = c[k];
            index ++;
        }
        
        return String.valueOf(ch);
    }
    
    // 这个方法实现了字符串中，把重复的子串都只用一个替换值替换，而不是去掉。
    public String removeRepetition(char[] ch, String to) {
        String result = "";
        String cur = "";
        
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] != 0) {
                cur = cur + String.valueOf(ch[i]);
            }
            
            if (ch[i] == 0 && (i == 0 || ch[i - 1] != 0)) {
                result = result + cur + to;
                cur = "";
            }
        }
        
        if (!cur.equals("")) {
            result = result + cur;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        String str = "123abcabc44abcabc5abcabc6abcabc";
        String from = "abc";
        String to = "XY";
        ReplaceStrInStr main = new ReplaceStrInStr();
        String res = main.replace(str, from, to);
        System.out.println(res);
    }

}
