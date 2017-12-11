package com.lu.string;

/**
 * 
 * @author lu
 * 
 * @description 判断两个字符串是否为变形词，如231和123返回true，231和2331返回false。
 *
 */
public class JudgeStringIsDeformation {

    // 该方法是把字符串转为字符数组，字符编码为0-255，用一个256长的数组来辅助，先遍历一遍，每次遇到字符就加一，之后再遍历一遍，每次遇到就减一，一旦有值小于0，说明字符数量不等，返回false，如果遍历完还没有false，则返回true。
    public boolean isDeformation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[] map = new int[256];
        
        for (int i = 0; i < c1.length; i++) {
            map[c1[i]] ++;
        }
        
        // map[] -- == 0，即是先判断再--，实际上是判断该数是否小于0，与-- map[] < 0效果相同。
        for (int i = 0; i < c2.length; i++) {
            if (map[c2[i]] -- == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        String s1 = "289";
        String s2 = "893";
        JudgeStringIsDeformation main = new JudgeStringIsDeformation();
        boolean res = main.isDeformation(s1, s2);
        System.out.println(res);
    }

}
