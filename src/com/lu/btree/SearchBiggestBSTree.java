package com.lu.btree;

/**
 * 
 * @author lu
 * 
 * @description 找出二叉树中最大的二叉查找树。
 *
 */
public class SearchBiggestBSTree {

    public TNode searchBiggestTree(TNode node) {
        int[] res = new int[3];
        return posOrderSearch(node, res);
    }
    
    
    // res数组的三个位置，分别表示节点数、最小值，最大值。
    // 不断递归，从下往上得到节点数，左/右边二叉查找树的最大/小值，当在每一层都满足左小右大的条件时，把左右的节点数相加并返回父节点，不如不满足该条件，则返回节点数多的那个头节点，并令节点数为多的那边的节点数。
    public TNode posOrderSearch(TNode node, int[] res) {
        if (node == null) {
            res[0] = 0;
            res[1] = Integer.MAX_VALUE;
            res[2] = Integer.MIN_VALUE;
            return null;
        }
        
        int value = node.value;
        TNode cur = node;
        TNode lCur = node.left;
        TNode rCur = node.right;
        TNode lNode = posOrderSearch(lCur, res);
        int lNodeNum = res[0];
        int lMin = res[1];
        int lMax = res[2];
        TNode rNode = posOrderSearch(rCur, res);
        int rNodeNum = res[0];
        int rMin = res[1];
        int rMax = res[2];
            
        res[1] = Math.min(lMin, value);
        res[2] = Math.max(rMax, value);
        
        // 这个返回过程，即从下到上一直累计二叉树，当不满足查找树条件时，返回那个节点多的二叉树，这样继续向上的过程中，也都是返回这个树，然后可能会再和整体的左边的二叉树相比。即为一个向上累计子树并一路比较合并的过程。
        if (lCur == lNode && rCur == rNode && lMax < value && value < rMin) {
            res[0] = lNodeNum + rNodeNum + 1;
            return node;
        }
        
        res[0] = Math.max(lNodeNum, rNodeNum);
        return lNodeNum > rNodeNum ? lNode : rNode;
    }
    
    /* 二叉树如图：     6
                /   \
               1     12
              / \   /   \
             0   3 10    13
                  /  \   / \  
                 4   14 20  16
                / \  / \
               2  5 11 15
    */
    public static void main(String[] args) {
        TNode node0 = new TNode(0);
        TNode node1 = new TNode(1);
        TNode node2 = new TNode(2);
        TNode node3 = new TNode(3);
        TNode node4 = new TNode(4);
        TNode node5 = new TNode(5);
        TNode node6 = new TNode(6);
        TNode node10 = new TNode(10);
        TNode node11 = new TNode(11);
        TNode node12 = new TNode(12);
        TNode node13 = new TNode(13);
        TNode node14 = new TNode(14);
        TNode node15 = new TNode(15);
        TNode node16 = new TNode(16);
        TNode node20 = new TNode(20);

        node6.left = node1;
        node6.right = node12;
        node1.left = node0;
        node1.right = node3;
        node12.left = node10;
        node12.right = node13;
        node10.left = node4;
        node10.right = node14;
        node4.left = node2;
        node4.right = node5;
        node14.left = node11;
        node14.right = node15;
        node13.left = node20;
        node13.right = node16;

        SearchBiggestBSTree main = new SearchBiggestBSTree();
        TNode rNode = main.searchBiggestTree(node6);
        main.preOrderIterate(rNode);
    }

    public void preOrderIterate(TNode node) {
        if (node == null) {
            return;
        }
        
        System.out.print(node.value + " ");
        preOrderIterate(node.left);
        preOrderIterate(node.right);
    }
    
}
