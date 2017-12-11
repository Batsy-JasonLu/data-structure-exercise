package com.lu.string;

/**
 * 
 * @author lu
 * 
 * @description 求字符串的中数字的和，遇到小数点忽略，遇到连续负号时，奇数个为负，偶数个为正，如a-1b--12，返回11。
 *
 */
public class CountSumInString {

    // 该方法中，每次得到字符，同时判断，如果不是数字，则sum加num，然后num为0，如果不是数字且为负号，看是否为连续负号，同时判断isminus的值，最后如果是数字，不断用num*10再加上cur，cur的正负号由isminus决定。
    // 另外最后再加一个num，防止最后一个数没有被加上。
    public int countSum(String str) {
        if (str == null) {
            return 0;
        }
        
        char[] c = str.toCharArray();
        int sum = 0;
        int num = 0;
        int cur = 0;
        boolean isMinus = false;
        
        // 字符-'0'操作，可以把数字字符转为int型。
        for (int i = 0; i < c.length; i++) {
            cur = c[i] - '0';
            
            if (cur < 0 || cur > 9) {
                sum = sum + num;
                num = 0;
                
                if (c[i] == '-') {
                    if (i - 1 > -1 && c[i - 1] == '-') {
                        isMinus = !isMinus;
                    } else {
                        isMinus = true;
                    }
                } else {
                    isMinus = false;
                }
            } else {
                num = num * 10 + (isMinus ? -cur : cur);
            }
        }
        sum = sum + num;
        
        return sum;
    }
    
    public static void main(String[] args) {
        String str = "a11b35c-16d2-6e15--7";
        CountSumInString main = new CountSumInString();
        int res = main.countSum(str);
        System.out.println(res);
    }

}
