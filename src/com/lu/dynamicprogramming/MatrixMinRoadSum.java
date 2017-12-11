package com.lu.dynamicprogramming;

/**
 * 
 * @author lu
 * 
 * @description 求矩阵的最大路径和，即给出一个矩阵，每个点上有一个数字，从左上角走到右下角，只能向右或向下走，记录走过的路径的数字和，返回最小的路径和。
 *
 */
public class MatrixMinRoadSum {

    // 该方法通过不断计算每个位置到下一个位置的路径和，然后再汇总起来比较。时间复杂度和空间复杂度都为o(m*n)。
    public int getMinSum(int[][] m) {
        if (m == null || m.length < 1 || m[0] == null || m[0].length < 0) {
            return 0;
        }
        
        int row = m.length;
        int col = m[0].length;
        int dp[][] = new int[row][col];
        dp[0][0] = m[0][0];
        
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }

        return dp[row - 1][col - 1];
    }
    
    // 该方法使用一个压缩数组不断滑动的方法，去保存路径和，这样虽然时间复杂度不会变，但是空间复杂度能降为o(min(m,n))。
    // 另外，遇到二维动态规划的题，几乎都能用该方法去压缩空间复杂度。
    public int getMinSumWithCompress(int[][] m) {
        if (m == null || m.length < 1 || m[0] == null || m[0].length < 0) {
            return 0;
        }
        
        int more = m.length >= m[0].length ? m.length : m[0].length;
        int less = m.length < m[0].length ? m[0].length : m.length;
        boolean isRowLess = m.length < m[0].length ? true : false;
        
        int[] compress = new int[less];
        compress[0] = m[0][0];
        
        for (int i = 1; i < less; i++) {
            compress[i] = isRowLess ? compress[i - 1] + m[i][0] : compress[i - 1] + m[0][i];
        }
        
        for (int i = 1; i < more; i++) {
            compress[0] = compress[0] + (isRowLess ? m[i][0] : m[0][i]); 
            for (int j = 1; j < less; j++) {
                compress[j] = Math.min(compress[j - 1], compress[j]) + (isRowLess ? m[i][j] : m[j][i]);
            }
        }
        
        return compress[less - 1];
    }
    
    public static void main(String[] args) {
        int[][] arr = {{1,3,5,9},{8,1,3,4},{5,0,6,1},{8,8,4,0}};
        MatrixMinRoadSum main = new MatrixMinRoadSum();
        int res = main.getMinSum(arr);
        System.out.println(res);
        int res2 = main.getMinSumWithCompress(arr);
        System.out.println(res2);
    }

}
