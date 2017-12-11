package com.lu.dynamicprogramming;

/**
 * 
 * @author lu
 * 
 * @description 斐波那契数列（从第3位开始，下一位数等于前两位之和，如1,1,2,3,5...），输入数字n，返回数列上第n位的值。
 *
 */
public class Fibonacci {

    // 最简单的递归，时间复杂度为o(2^n)。
    public int f1(int n) {
        if (n < 1) {
            return 0;
        }
        
        if (n == 1 || n == 2) {
            return 1;
        }
        
        return f1(n - 2) + f1(n - 1);
    }
    
    // 循环，时间复杂度为o(n)。
    public int f2(int n) {
        if (n < 1) {
            return 0;
        }
        
        if (n == 1 || n == 2) {
            return 1;
        }
        
        int res = 1;
        int pre = 1;
        int tmp = 0;
        
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        
        return res;
    }
    
    // 矩阵乘法，时间复杂度为o(logn)。
    public int f3(int n) {
        if (n < 1) {
            return 0;
        }
        
        if (n == 1 || n == 2) {
            return 1;
        }
        
        int[][] base = {{1,1},{1,0}};
        int[][] res = matrixPower(base, n - 2);
        
        return res[0][0] + res[1][0];
    }
    
    // 该方法是求矩阵m的p次方。
    public int[][] matrixPower(int[][] m, int p) {
        int [][] res = new int[m.length][m[0].length];
        
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        
        int [][] tmp = m;
        
        // p >>= 1等于p = p >> 1，即p右移一位，即p除以2。
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix(res, tmp);
            } else {
                tmp = muliMatrix(tmp, tmp);
            }
        }
        
        return res;
    }
    
    // 该方法是两个矩阵相乘的具体实现。
    public int[][] muliMatrix(int[][] m1, int[][] m2) {
        int res[][] = new int[m1.length][m2[0].length];
        
        for (int i = 0; i < m2[0].length; i++) {
            for (int j = 0; j < m1.length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        Fibonacci main = new Fibonacci();
        int res1 = main.f1(6);
        System.out.println(res1);
        int res2 = main.f2(6);
        System.out.println(res2);
        int res3 = main.f3(6);
        System.out.println(res3);
    }
    
}
