package com.lu.btree;

/**
 * 
 * @author lu
 * 
 * @description 判断二叉树是否为平衡二叉树，即任何一个节点的左右子树的高度差的绝对值不超过1。
 *
 */
public class JudgeBTreeBalance {

    public boolean isBalance(TNode node) {
        boolean[] res = new boolean[1];
        res[0] = true;
        getHeight(node, 1, res);
        return res[0];
    }
    
    public int getHeight(TNode node, int level, boolean[] res) {
        if (node == null) {
            return level;
        }
        
        int lHeight = getHeight(node.left, level + 1, res);
        
        if (!res[0]) {
            return level;
        }
                
        int rHeight = getHeight(node.right, level + 1, res);
        
        if (!res[0]) {
            return level;
        }
        
        if (Math.abs(lHeight - rHeight) > 1) {
            res[0] = false;
        }
        
        return Math.max(lHeight, rHeight);
    }
    
    /* 二叉树如图：     1
                /   \
               2     3
              / \   /
             4   5 6
                  /
                 7
    */
    public static void main(String[] args) {
        TNode node1 = new TNode(1);
        TNode node2 = new TNode(2);
        TNode node3 = new TNode(3);
        TNode node4 = new TNode(4);
        TNode node5 = new TNode(5);
        TNode node6 = new TNode(6);
        TNode node7 = new TNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node6.left = node7;

        JudgeBTreeBalance main = new JudgeBTreeBalance();
        boolean res = main.isBalance(node1);
        System.out.println(res);
    }

}
