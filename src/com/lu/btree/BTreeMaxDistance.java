package com.lu.btree;

/**
 * 
 * @author lu
 * 
 * @description 二叉树两个节点间的最大距离。
 *
 */
public class BTreeMaxDistance {

    // 用后序遍历，求出head的左/右子树的最大距离，再求出head.left/right到最左/右的距离，相加并加一，比较三者的得出结果。
    public int getMaxDistance(TNode node) {
        if (node == null) {
            return 0;
        }
        
        int[] record = new int[1];
        return getMax(node, record);
    }
    
    public int getMax(TNode node, int[] record) {
        if (node == null) {
            record[0] = 0;
            return 0;
        }

        int lMax = getMax(node.left, record);
        int maxFromLeft = record[0];
        int rMax = getMax(node.right, record);
        int maxFromRight = record[0];
        int curMax = maxFromLeft + 1 + maxFromRight;
        record[0] = Math.max(maxFromLeft, maxFromRight) + 1;
        
        return Math.max(Math.max(lMax, rMax), curMax);
    }
    
    /* 二叉树如图：      1
                /    \
               2      3
              / \     / \ 
             4   5   6   9 
                / \        
               7  8       
    */
    public static void main(String[] args) {
        TNode node1 = new TNode(1);
        TNode node2 = new TNode(2);
        TNode node3 = new TNode(3);
        TNode node4 = new TNode(4);
        TNode node5 = new TNode(5);
        TNode node6 = new TNode(6);
        TNode node7 = new TNode(7);
        TNode node8 = new TNode(8);
        TNode node9 = new TNode(9);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node5.left = node7;
        node5.right = node8;
        node3.left = node6;
        node3.right = node9;

        BTreeMaxDistance main = new BTreeMaxDistance();
        int res = main.getMaxDistance(node1);
        System.out.println(res);
    }
    
}
