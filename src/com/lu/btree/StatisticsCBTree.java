package com.lu.btree;

/**
 * 
 * @author lu
 * 
 * @description 统计完全二叉树的节点数，要求时间复杂度小于o(n)。
 *
 */
public class StatisticsCBTree {

    public int statisticsCBTree(TNode node) {
        if (node == null) {
            return 0;
        }
        
        return bs(node, 1, mostLeftNodeLevel(node, 1));
    }
    
    // 该方法是找头节点的右子树的最底层的最左节点，并记下层数，如果层数与h相同，说明左边全满，节点数为左满二叉树+头节点+递归遍历右边的二叉树。如果层数不等于h，则左边未满，节点数为右满二叉树+头节点+递归遍历左边的二叉树。时间复杂度为o(h^2)。
    public int bs(TNode node, int level, int h) {
        if (level == h) {
            return 1;
        }
        
        if (mostLeftNodeLevel(node.right, level + 1) == h) {
            return (1 << (h - level)) + bs(node.right, level + 1, h);
        } else {
            return (1 << (h - level - 1)) + bs(node.left, level + 1, h);
        }
    }
    
    public int mostLeftNodeLevel(TNode node, int level) {
        while (node != null) {
            level ++;
            node = node.left;
        }
        
        return level - 1;
    }
    
    /* 二叉树如图：      1
                /    \
               2      3
              / \     / \ 
             4   5   6   9 
            / \        
           7   8       
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
        node4.left = node7;
        node4.right = node8;
        node3.left = node6;
        node3.right = node9;

        StatisticsCBTree main = new StatisticsCBTree();
        int res = main.statisticsCBTree(node1);
        System.out.println(res);
    }
    
}
