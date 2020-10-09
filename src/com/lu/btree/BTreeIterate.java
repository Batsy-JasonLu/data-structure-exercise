package com.lu.btree;

import java.util.Stack;

/**
 * 
 * @author lu
 * 
 * @description 用递归和非递归的方式，遍历二叉树。
 *
 */
public class BTreeIterate {

    // 递归的方法要注意，当node=null时要return。
    public void preOrderIterateByRecursive(TNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        preOrderIterateByRecursive(node.left);
        preOrderIterateByRecursive(node.right);
    }
    
    public void inOrderIterateByRecursive(TNode node) {
        if (node == null) {
            return;
        }
        inOrderIterateByRecursive(node.left);
        System.out.print(node.value + " ");
        inOrderIterateByRecursive(node.right);
    }
    
    public void posOrderIterateByRecursive(TNode node) {
        if (node == null) {
            return;
        }
        posOrderIterateByRecursive(node.left);
        posOrderIterateByRecursive(node.right);
        System.out.print(node.value + " ");
    }
    
    // 非递归先序，入栈，栈非空循环，出栈顶并打印，把栈顶的right和left入栈。
    public void preOrderIterate(TNode node) {
        Stack<TNode> stack = new Stack<TNode>();
        stack.add(node);
        while (!stack.isEmpty()) {
            TNode cur = stack.pop();
            System.out.print(cur.value + " ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }
    
    // node和stack非空循环，node非空则入栈并往下找left，否则出栈顶打印，再找right。
    public void inOrderIterate(TNode node) {
        Stack<TNode> stack = new Stack<TNode>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.print(node.value + " ");
                node = node.right;
            }
        }
    }
    
    // 后序非递归（双栈），入栈s1，s1非空循环，出栈顶入栈s2，left和right依次入栈s1，最后s2出栈打印。
    public void posOrderIterateByTwoStacks(TNode node) {
        Stack<TNode> stack1 = new Stack<TNode>();
        Stack<TNode> stack2 = new Stack<TNode>();

        TNode cur = null;
        stack1.push(node);
        while (!stack1.isEmpty()) {
            cur = stack1.pop();
            stack2.push(cur);
            
            if (cur.left != null) {
                stack1.push(cur.left);
            }
            
            if (cur.right != null) {
                stack1.push(cur.right);
            }
        }
        
        TNode cur2 = null;
        while (!stack2.isEmpty()) {
            cur2 = stack2.pop();
            System.out.print(cur2.value + " ");
        }
    }
    
    // 非递归后序（单栈），head赋给h，h入栈，取栈顶为c，如果h与c的left和right都不等，则c.left入栈，h与c的right不等在，则c.right入栈，否则打印并h=c。
    public void posOrderIterateByOneStack(TNode node) {
        Stack<TNode> stack = new Stack<TNode>();
        TNode h = node;
        TNode c = null;
        
        stack.push(h);
        while (!stack.isEmpty()) {
            c = stack.peek();
            
            if (c.left != null && c.left != h && c.right != h) {
                stack.push(c.left);
            } else if (c.right != null && c.right != h) {
                stack.push(c.right);
            } else {
                System.out.print(stack.pop().value + " ");
                h = c;
            }
        }
        
    }
    
    /* 二叉树如图：
                  1
                /   \
               2     3
              / \   /
             4   5 6
    */
    public static void main(String[] args) {
        TNode node1 = new TNode(1);
        TNode node2 = new TNode(2);
        TNode node3 = new TNode(3);
        TNode node4 = new TNode(4);
        TNode node5 = new TNode(5);
        TNode node6 = new TNode(6);
        
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        
        BTreeIterate main = new BTreeIterate();
        main.preOrderIterateByRecursive(node1);
        System.out.println();
        main.inOrderIterateByRecursive(node1);
        System.out.println();
        main.posOrderIterateByRecursive(node1);
        System.out.println();
        
        main.preOrderIterate(node1);
        System.out.println();
        main.inOrderIterate(node1);
        System.out.println();
        main.posOrderIterateByTwoStacks(node1);
        System.out.println();
        main.posOrderIterateByOneStack(node1);
    }

}

