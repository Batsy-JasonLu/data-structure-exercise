package com.lu.dynamicprogramming;

/**
 * 
 * @author lu
 * 
 * @description 假设农场中有一只小母牛，每年会生一只小母牛，且不会死，小母牛3年后又可以生小母牛，给定年数n，求出牛的数量。
 *
 */
public class Calve {

    // 这个问题可以排出1,2,3,4,6,9...满足了c(n)=c(n-1)+c(n-3)的情况，所以可以按照斐波那契问题的方法解决。
    // 另外，只要等递归式满足f(n)=a*f(n-1)+b*f(n-2)...+k*f(n-i)，则说明它是一个i阶递推式，可以通过i*i的矩阵乘法表达，并用其把时间复杂度降为o(logn)。
    public int c1(int n) {
        if (n < 1) {
            return 0;
        }
        
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        
        return c1(n - 1) + c1(n - 3);
    }
    
    public int c2(int n) {
        if (n < 1) {
            return 0;
        }
        
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        
        return res;
    }
    
    public int c3(int n) {
        if (n < 1) {
            return 0;
        }
        
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        
        int[][] base = {{1,1,0},{0,0,1},{1,0,0}};
        int[][] res = matrixPower(base, n-3);
        
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
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
        Calve main = new Calve();
        int res1 = main.c1(6);
        System.out.println(res1);
        int res2 = main.c2(6);
        System.out.println(res2);
        int res3 = main.c3(6);
        System.out.println(res3);
    }
    
}
