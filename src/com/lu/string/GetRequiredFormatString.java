package com.lu.string;

import java.util.HashMap;

/**
 * 
 * @author lu
 * 
 * @description 找出指定要求的字符串。指定字符串的定义是，字符串长为1或2，可为单个小写a；或者大写+小写Ab；或者大写+大写AA，给一个位置k，返回该位置上的字符串。如str=aaABCDEcBCg，k=7返回Ec，k=10返回g。
 *
 */
public class GetRequiredFormatString {

    // 与原书方法不同，先把字符串划分成指定字符串，放到map中，再找相应位置的字符。
    public String getStr(String str, int k) {
        if (str == null || k < 0) {
            return null;
        }
        
        char[] c = str.toCharArray();
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= 'a' && c[i] <= 'z') {
                if (i - 1 > -1 && map.get(i - 1) == null && c[i - 1] >= 'A' && c[i - 1] <= 'Z') {
                    map.put(i, String.valueOf(c[i - 1]) + String.valueOf(c[i]));
                    map.put(i - 1, String.valueOf(c[i - 1]) + String.valueOf(c[i]));
                } else {
                    map.put(i, String.valueOf(c[i]));
                }
            } else if (c[i] >= 'A' && c[i] <= 'Z') {
                if (i - 1 > -1 && map.get(i - 1) == null && c[i - 1] >= 'A' && c[i - 1] <= 'Z') {
                    map.put(i, String.valueOf(c[i - 1]) + String.valueOf(c[i]));
                    map.put(i - 1, String.valueOf(c[i - 1]) + String.valueOf(c[i]));
                }
            }
        }
        
        return map.get(k);
    }
    
    public static void main(String[] args) {
        String str = "aaABCDEcBCg";
        GetRequiredFormatString main = new GetRequiredFormatString();
        String res1 = main.getStr(str, 7);
        String res2 = main.getStr(str, 4);
        String res3 = main.getStr(str, 10);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }

}
