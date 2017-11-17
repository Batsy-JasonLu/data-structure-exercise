package com.lu.btree;

public class SearchTwoNodesLatestParent {

    public TNode searchParent(TNode node1, TNode node2) {
        if (node1 == null || node2 == null) {
            return null;
        }
        
        
        
    }
    
    /* 二叉树如图：      2
                /    \
               1      9
                \    /  \
                 3  4    8
                     \   / \  
                      6 7   5
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

        node2.left = node1;
        node2.right = node9;
        node1.right = node3;
        node9.left = node4;
        node9.right = node8;
        node4.right = node6;
        node8.left = node7;
        node8.right = node5;

        SearchTwoErrorNode main = new SearchTwoErrorNode();
        TNode[] res = main.searchErrNode(node2);
        main.printArr(res);
    }

}
