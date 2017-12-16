package com.lu.string;

/**
 * 
 * @author lu
 * 
 * @description 给定一个由0和1字符组成的整数，求满足0的左边是1的字符的数量，如n=2，则有00,01,10,11，其中10,11满足条件，返回2。
 *
 */
public class ZeroOneStrNum {

    // 暴力递归方法，时间复杂度为o(2^n*n)，就不讲了。
    // 本方法时间复杂度为o(2^n)，假设p(i)表示0~i-1的字符满足条件，且i为1时，用剩余的i~n-1能组成多少种满足条件的字符，所以问题就变成了求p(1)。
    // 当i=1时，则i-1=0，确定i-1位置上要为1，这时如i位置上为0，则满足条件的字符串为p(i+1)，否则为0的话，i+1位置上必须为1，则满足条件的字符串为p(i+2)，即得到p(i)=p(i+1)+p(i+2)。
    // 当走到倒数第二个字符时，最后可以为0或1，所以p(n-1)=2，而到最后一个字符时，只有一种可能，所以p(n)=1。
    // 初步得到结果后，可以发现这就是一个斐波那契数列，只是开头两个数有区别，所以接着可以用斐波那契数列的解法，把时间复杂度降为o(logn)，这里就不写了。
    public int getStrNum(int n) {
        if (n <= 0) {
            return 0;
        }
        
        return process(1, n);
    }
    
    public int process(int i, int n) {
        if (n - 1 == i) {
            return 2;
        }
        
        if (n == i) {
            return 1;
        }
        
        return process(i + 1, n) + process(i + 2, n);
    }
    
    public static void main(String[] args) {
        ZeroOneStrNum main = new ZeroOneStrNum();
        int res = main.getStrNum(6);
        System.out.println(res);
    }

}
