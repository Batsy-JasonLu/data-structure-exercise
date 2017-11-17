package com.lu.btree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class JudgeIsBSTreeAndCBTree {

    // 用中序遍历，如果节点值是递增的，说明为二叉搜索树。
    public boolean isBSTree(TNode node) {
        if (node == null) {
            return true;
        }
        
        Stack<TNode> stack = new Stack<TNode>();
        TNode pre = null;
        
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                
                if (pre != null && node.value < pre.value) {
                    return false;
                }
                
                pre = node;
                node = node.right;
            }
        }
        
        return true;
    }
    
    // 层析遍历，如果有右孩子没有左孩子，直接返回false；如果不是左右孩子都有，则之后的节点必须为叶子节点。
    public boolean isCBTree(TNode node) {
        if (node == null) {
            return true;
        }
        
        Queue<TNode> queue = new LinkedList<TNode>();
        queue.offer(node);
        TNode lNode = null;
        TNode rNode = null;
        boolean leaf = false;
        
        while (!queue.isEmpty()) {
            TNode cur = queue.poll();
            lNode = cur.left;
            rNode = cur.right;
            
            if (lNode == null && rNode != null) {
                return false;
            }
            
            if ((lNode != null || rNode != null ) && leaf) {
                return false;
            }
            
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            
            // 用leaf来判断是否到叶子节点了，在遍历时，当右子节点都为空，说明下一层的左边的树和本层右边的树，都是叶子节点。
            // 这是一个常规的操作，记住。
            if (cur.right != null) {
                queue.offer(cur.right);
            } else {
                leaf = true;
            }
        }
        
        return true;
    }
    
    /* 二叉树如图：     2
                /   \
               1     5
                \     \
                 3     6
    
          注意！搜索二叉树的定义是，左/右子树上的所有节点值均小于/大于根节点值，要所有都大于或小于，像我上面的例子中，3就大于了2，不满足bst的条件，所有其实并不是二叉搜索树。
    
    */
    public static void main(String[] args) {
        TNode node1 = new TNode(1);
        TNode node2 = new TNode(2);
        TNode node3 = new TNode(3);
        TNode node4 = new TNode(4);
        TNode node5 = new TNode(5);
        TNode node6 = new TNode(6);
        TNode node7 = new TNode(7);

        node2.left = node1;
        node2.right = node5;
        // 有该节点则不满足bst。
//         node1.right = node3;
        node5.right = node6;

        JudgeIsBSTreeAndCBTree main = new JudgeIsBSTreeAndCBTree();
        boolean res1 = main.isBSTree(node2);
        System.out.println(res1);
        boolean res2 = main.isCBTree(node2);
        System.out.println(res2);
    }

}
