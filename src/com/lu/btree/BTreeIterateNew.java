package com.lu.btree;

import java.util.*;

/**
 *
 * @author lu
 *
 * @description 用非递归的方式（新），遍历二叉树。此方法更加同一格式。
 *
 */
public class BTreeIterateNew {

    public List<Integer> preOrderTraverse(TNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                res.add(root.value);
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                root = root.right;
            }
        }
        return res;
    }

    public List<Integer> inOrderTraverse(TNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                res.add(root.value);
                root = root.right;
            }
        }
        return res;
    }

    public List<Integer> postOrderTraverse(TNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TNode> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int i = 1;
        while (root != null || !stack1.isEmpty()) {
            while (root != null) {
                stack1.push(root);
                stack2.push(0);
                root = root.left;
            }

            while (!stack1.isEmpty() && stack2.peek() == i) {
                stack2.pop();
                res.add(stack1.pop().value);
            }

            if (!stack1.isEmpty()) {
                stack2.pop();
                stack2.push(1);
                root = stack1.peek();
                root = root.right;
            }
        }
        return res;
    }


    public List<Integer> levelOrderTraverse(TNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        Queue<TNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TNode node = queue.poll();
            res.add(node.value);

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
               queue.add(node.right);
            }
        }
        return res;
    }

    /*
            1
           / \
          2   3
         /   / \
        4   5   6
    */
    public static void main(String[] args) {
        TNode root = new TNode(1);
        TNode node2 = new TNode(2);
        TNode node3 = new TNode(3);
        TNode node4 = new TNode(4);
        TNode node5 = new TNode(5);
        TNode node6 = new TNode(6);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        BTreeIterateNew b = new BTreeIterateNew();
//        b.print(b.preOrderTraverse(root));
//        b.print(b.inOrderTraverse(root));
//        b.print(b.postOrderTraverse(root));
        b.print(b.levelOrderTraverse(root));
    }

    private void print(List<Integer> list) {
        for (Integer val : list) {
            System.out.print(val + " ");
        }
    }

}
