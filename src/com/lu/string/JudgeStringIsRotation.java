package com.lu.string;

import com.lu.other.KMP;

/**
 * 
 * @author lu
 * 
 * @description 判断两个字符串是否为旋转词，如abcd和cdab，返回true，abcd和cdba返回false。
 *
 */
public class JudgeStringIsRotation {

    // 该方法是记下两字符串开始相同的位置，然后再循环一遍，比较不相同的部分，这样时间复杂度为o(n^2)。
    // 要想把时间复杂度降为o(n)，可以用kmp算法作为辅助。
    public boolean isRotation(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int index1 = -1;
        int index2 = -1;
        
        for (int i = 0; i < c2.length; i++) {
            for (int j = 0; j < c1.length; j++) {
                if (c2[i] == c1[j]) {
                    index1 = index1 == -1 ? j : index1;
                    index2 = index2 == -1 ? j : index2;
                } 
            }
        }
        
        if (index1 == 0) {
            return true;
        } else {
            for (int i = 0; i < index1; i++) {
                if (c1[i] != c2[index2]) {
                    return false;
                } else {
                    index2 ++;
                }
            }
        }
        
        return true;
    }
    
    // 用kmp做辅助方法，把其中一个字符串相加，如s = s1 + s1，然后问题就会变成s2是否为s的子串，是则为旋转词。
    public boolean isRotation2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        
        String s3 = s2 + s2;
        
        return KMP.kmpSearch(s3, s1) == -1 ? false : true;
    }
    
    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = "cdab";
        String s3 = "cdba";
        String s4 = "abcd";
        JudgeStringIsRotation main = new JudgeStringIsRotation();
        boolean res = main.isRotation(s1, s2);
        System.out.println(res);
        boolean res2 = main.isRotation(s1, s3);
        System.out.println(res2);
        boolean res3 = main.isRotation(s1, s4);
        System.out.println(res3);
        System.out.println("---by KMP");
        boolean res4 = main.isRotation2(s1, s2);
        System.out.println(res4);
        boolean res5 = main.isRotation2(s1, s3);
        System.out.println(res5);
        boolean res6 = main.isRotation2(s1, s4);
        System.out.println(res6);
       
    }

}
