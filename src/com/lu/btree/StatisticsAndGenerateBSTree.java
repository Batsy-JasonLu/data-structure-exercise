package com.lu.btree;

/**
 * 
 * @author lu
 * 
 * @description 输入一个数n，1表示数组{1}，2表示数组{1,2}，3表示数组{1,2,3}，以此类推，若以这个数组为中序遍历结果吗，求可能的二叉树结构有多少种；进阶返回所有可能的结构的头节点。
 *
 */
public class StatisticsAndGenerateBSTree {

    // 求二叉树可能的种数，因为数组都是1,2,3...这样的有序整数，当中序遍历是有序无重复的元素时，则为二叉查找树。
    // 而它可能的结构，则有3种情况，当1为头节点时，种数取决于右子树有多少种可能，即num(n-1)；当i为头节点时，左子树i-1，右子树为n-i，则种数为num(i-1)*num(n-i)；当n为头节点时，种数取决于左子树，即num(n-1)，累加起来，用动态规划完成。
    public int statisticsBSTree(int n) {
        if (n < 2) {
            return 1;
        }
        
        int[] num = new int[n + 1];
        num[0] = 1;
        
        for (int i = 1; i < num.length; i++) {
            for (int j = 1; j < i + 1; j++) {
                num[i] += num[j - 1] * num[i - j];
            }
        }

        return num[n];
    }
    
    // TODO 返回所有可能的结构的头节点。
    
    public static void main(String[] args) {
        StatisticsAndGenerateBSTree main = new StatisticsAndGenerateBSTree();
        int res = main.statisticsBSTree(2);
        System.out.println(res);
    }
    
}
