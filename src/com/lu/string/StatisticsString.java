package com.lu.string;

/**
 * 
 * @author lu
 * 
 * @description 在字符串中统计字符，如输入aaabbbacc，返回a_3_b_3_a_1_c_2。
 *
 */
public class StatisticsString {

    // 每当遇到重复字符的问题，基本有会有下面的循环情况，即循环中获取重复字符的开头和重复的长度，当不再重复时，在里面循环操作完成，之后开头和长度重新置为0，继续遍历到最后。
    public String statistics(String str) {
        if (str == null) {
            return null;
        }
        
        char[] c = str.toCharArray();
        int count = 0;
        int start = -1;
        String res = "";
        String cur = "";
        
        for (int i = 1; i < c.length; i++) {
            if (c[i - 1] == c[i]) {
                start = start == -1 ? i : start;
                count ++;
            } else {
                if (count != 0) {
                    cur = "_" + c[start] + "_" + (count + 1);
                    res = res + cur;
                    count = 0;
                    start = -1;
                }
            }
        }
        
        if (count != 0) {
            cur = "_" + c[start] + "_" + (count + 1);
            res = res + cur;
            count = 0;
            start = -1;
        }
        
        return res.substring(1, res.length());
    }
    
    public static void main(String[] args) {
        String str = "aaabbbadddff";
        StatisticsString main = new StatisticsString();
        String res = main.statistics(str);
        System.out.println(res);
    }

}
