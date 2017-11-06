package com.lu.linkedlist;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author lu
 * 
 * @description 二叉搜索树/查找树/排序树（即同一个概念），转换为一个双向链表。
 *
 */
public class BSTreeToLinkedList {

    public TreeNode convert(TreeNode head) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        inOrderToQueue(head, queue);
        
        // 先处理第一个节点，因为它只有right指针。
        TreeNode cur = queue.poll();
        TreeNode node = cur;
        TreeNode pre = cur;
        pre.left = null;
        
        // 逆序/删除/连接操作，都需要用到当前节点的前一个节点，所以都额外多使用一个指针指向当前节点的前一个节点，这里是用当前节点去指向前一个节点，再由前一个节点指向当前节点，完成连接。
        while (!queue.isEmpty()) {
            cur = queue.poll();
            cur.left = pre;
            pre.right = cur;
            pre = cur;
        }
        
        // 最后一个节点，它的right指针指向null。
        pre.right = null;
        return node;
    }
    
    public void inOrderToQueue(TreeNode node, Queue<TreeNode> queue) {
        if (node == null) {
            return;
        }
        
        inOrderToQueue(node.left, queue);
        queue.add(node);
        inOrderToQueue(node.right, queue);
    }
    
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        
        node6.left = node4;
        node6.right = node7;
        
        node4.left = node2;
        node4.right = node5;
        
        node2.left = node1;
        node2.right = node3;
        
        node7.right = node9;
        
        node9.left = node8;
        
        BSTreeToLinkedList main = new BSTreeToLinkedList();
        TreeNode rNode = main.convert(node6);
        main.printList(rNode);
    }
    
    public void printList(TreeNode head) {
        TreeNode tmp = head;
        while (tmp != null) {
            System.out.print(tmp.value + " ");
            tmp = tmp.right;
        }
        System.out.println();
    }

}

class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;
    
    public TreeNode(int value) {
        this.value = value;
    }
}
