package com.lu.dynamicprogramming;

/**
 * 
 * @author lu
 * 
 * @description 跳台阶问题，输入台阶数n，一次可以跨2个或1个台阶，有多少种跳法。
 *
 */
public class JumpStep {

    // 跳台阶问题，实际上是斐波那契问题的变换，台阶只有1,2阶时，跳法只有1,2种，当为n时，要看跳上最后一台的情况，要么从n-2跳2台，要么从n-1跳1台。
    // 这样，问题就成了，跳到n-2的方法数加n-1的方法数的求解，即可抽象为f(n)=f(n-1)+f(n-2)，再修改一个初始项，即可写成递归，然后类似地，写出循环和矩阵乘法。
    // 
    public int j1(int n) {
        if (n < 1) {
            return 0;
        }
        
        if (n == 1) {
            return 1;
        }
        
        if (n == 2) {
            return 2;
        }
        
        return j1(n - 1) + j1(n - 2);
    }
    
    public int j2(int n) {
        if (n < 1) {
            return 0;
        }
        
        if (n == 1) {
            return 1;
        }
        
        if (n == 2) {
            return 2;
        }
        
        int res = 2;
        int pre = 1;
        int tmp = 0;
        
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        
        return res;
    }
    
    public int j3(int n) {
        if (n < 1) {
            return 0;
        }
        
        if (n == 1) {
            return 1;
        }
        
        if (n == 2) {
            return 2;
        }
        
        int[][] base = {{1,1},{1,0}};
        int[][] res = matrixPower(base, n-2);
        
        return 2 * res[0][0] + res[1][0];
    }
    
    // 该方法是求矩阵m的p次方。
    public int[][] matrixPower(int[][] m, int p) {
        int [][] res = new int[m.length][m[0].length];
        
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        
        int [][] tmp = m;
        
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
        JumpStep main = new JumpStep();
        int res1 = main.j1(6);
        int res2 = main.j2(6);
        int res3 = main.j3(6);

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }

}
