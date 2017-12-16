package com.lu.string;

/**
 * 
 * @author lu
 * 
 * @description 给出字符串str和exp，看exp能不能和str匹配，其中，str不能含.和*，exp可以含，但是*不能在开头，且不能连续出现，.代表任意一个字符，*代表前面一个字符可以出现0次或多次。如str=abcd,exp=.*就能返回true；str="",exp="..*"返回false。
 *
 */
public class StringMatch {

    public boolean isValid(String str, String exp) {
        if (exp == null) {
            return false;
        }
        
        if (exp.indexOf("*") == 0) {
            return false;
        }
        
        char[] s = str.toCharArray();
        
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '*' || s[i] == '.') {
                return false;
            }
        }
        
        char[] c = exp.toCharArray();
        
        for (int i = 0; i < c.length; i++) {
            if (i - 1 > -1 && c[i] == '*' && c[i - 1] == '*') {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean isMatch(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        
        if (!isValid(str, exp)) {
            return false;
        }
        
        return process(s, e, 0, 0);
    }
    
    public boolean process(char[] s, char[] e, int si, int ei) {
        if (ei == e.length) {
            return si == s.length ? true : false;
        }
        
        if (ei + 1 == e.length || e[ei + 1] != '*') {
            return si != s.length && (e[ei] == s[si] || e[ei] == '.') && process(s, e, si + 1, ei + 1);
        }
        
        while (si != s.length && (e[ei] == s[si] || e[ei] == '.')) {
            if (process(s, e, si, ei + 2)) {
                return true;
            }
            si++;
        }
        
        return process(s, e, si, ei + 2);
    }
    
    public static void main(String[] args) {

    }

}
