package com.lu.bitwise;

/**
 * 
 * @author lu
 * 
 * @description 不用额外的变量交换两个整数的值。
 *
 */
public class ExchangeInteger {

    // 用异或 ^ 来完成，
    public void exchange(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        
        System.out.println("a:" + a + " " + "b:" + b);
    }
    
    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        ExchangeInteger main = new ExchangeInteger();
        main.exchange(a, b);
    }

}
