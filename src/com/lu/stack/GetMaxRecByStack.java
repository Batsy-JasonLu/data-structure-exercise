package com.lu.stack;

import java.util.Stack;

/**
 * 
 * @author lu
 *
 * @description 给定一个值只有0,1的矩阵，求用1构成的多个矩阵中，最大的矩阵的大小，矩阵大小为n*m，则要求时间复杂为O(n*m)。
 *
 */
public class GetMaxRecByStack {

    // 这个部分，把二维数组，从左到右，依次相加，每次加一列（二维数组的第一个括号固定，并遍历第二个括号，是按列遍历），就求出这几列的最大矩阵的大小。
    public int getMaxRec(int[][] arr) {
        int height[] = new int[arr.length];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                height[j] = arr[i][j] == 1 ? height[j] + 1 : 0;
            }
            max = Math.max(max, getCurHeightMaxRec(height));
        }
        return max;
    }
    
    // 该方法用来求出当前矩阵的最大矩阵的大小，用一个栈来做复辅助，比栈顶大的元素直接插入，小的则让栈顶出栈直到能把元素插入，这个过程中求出出栈元素的大小。
    public int getCurHeightMaxRec(int[] arr) {
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int cur = (i - k - 1) * arr[j];
                max = Math.max(cur, max);
            }
            stack.push(i);
        }
        
        while(!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int cur = (arr.length - k - 1) * arr[j];
            max = Math.max(cur, max);
        }
        
        return max;
    }
    
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 4, 3, 5};
        // 大括号里面的括号表示第0行，第1行...从上到下各行的数值。
        // 二维数组的两个[][]，分别是行、列。
        int[][] array = {{0,1,0,0,0,1},{0,0,1,1,0,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{0,0,1,0,0,1}};
        GetMaxRecByStack getMaxRecByStack = new GetMaxRecByStack();
        int max = getMaxRecByStack.getMaxRec(array);
        System.out.println(max);
    }

}
