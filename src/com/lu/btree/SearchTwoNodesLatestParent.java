package com.lu.btree;

/**
 * 
 * @author lu
 * 
 * @description 找到两个节点的最近公共祖先。进阶问题：减少查找的时间。再进阶：若二叉树节点为n，查询条数为m，要求时间复杂度为O(n+m)。
 * 
 *
 */
public class SearchTwoNodesLatestParent {

    // 普通查找中，直接后序递归遍历二叉树，只要head等于null/node1/node2时，都返回head。之后遍历到左/右子节点，都不为null时，返回head，否则返回不为空的那个。
    public TNode searchParent(TNode head, TNode node1, TNode node2) {
        if (head == null || head == node1 || head == node2) {
            return head;
        }
        
        TNode left = searchParent(head.left, node1, node2);
        TNode right = searchParent(head.right, node1, node2);
        
        if (left != null && right != null) {
            return head;
        }
        
        return left != null ? left : right;
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

        SearchTwoNodesLatestParent main = new SearchTwoNodesLatestParent();
        TNode res = main.searchParent(node2, node6, node8);
        System.out.println(res.value);
    }

}
