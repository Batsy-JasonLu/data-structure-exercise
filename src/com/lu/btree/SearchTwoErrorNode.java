package com.lu.btree;

import java.util.Stack;

/**
 * 
 * @author lu
 * 
 * @description 找出搜索二叉树中的两个错误节点。进阶问题，交换两个错误节点。
 *
 */
public class SearchTwoErrorNode {

    // 一棵二叉搜索树中，如果有两个节点交换后出错，则中序遍历是，一定会出现节点降序，且第一个节点是降序中值较大的节点，第二个节点是降序中值较小的节点。
    public TNode[] searchErrNode(TNode node) {
        if (node == null) {
            throw new RuntimeException();
        }
        
        TNode[] err = new TNode[2];
        Stack<TNode> stack = new Stack<TNode>();
        TNode pre = null;
        
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                
                if (pre != null && pre.value > node.value) {
                    err[0] = err[0] == null ? pre : err[0];
                    err[1] = node;
                }
                pre = node;
                node = node.right;
            }
        }
        
        return err;
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
    
    public void printArr(TNode[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i].value + " ");
        }
    }
    
}
