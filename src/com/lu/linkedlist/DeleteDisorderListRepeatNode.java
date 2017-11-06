package com.lu.linkedlist;

import java.util.HashSet;

/**
 * 
 * @author lu
 * 
 * @description 删除无序单链表中的重复节点，要求时间复杂度为o(n)。
 *
 */
public class DeleteDisorderListRepeatNode {

    public Node deleteRepeatNodeByHash(Node head) {
        if (head == null) {
            throw new RuntimeException();
        }
        
        HashSet<Integer> set = new HashSet<Integer>();
        Node pre = head;
        set.add(head.value);
        Node cur = head.next;
        
        // 需要用一个pre指针来保存前面最近的一个不重复的节点。
        while (cur != null) {
            if (set.contains(cur.value)) {
                pre.next = cur.next;
            } else {
                set.add(cur.value);
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
    
    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(3);
        node.next.next.next.next = new Node(4);
        node.next.next.next.next.next = new Node(4);
        node.next.next.next.next.next.next = new Node(2);
        node.next.next.next.next.next.next.next = new Node(1);
        node.next.next.next.next.next.next.next.next = new Node(1);
        
        DeleteDisorderListRepeatNode main = new DeleteDisorderListRepeatNode();
        Node rNode = main.deleteRepeatNodeByHash(node);
        main.printList(rNode);
    }
    
    public void printList(Node head) {
        Node tmp = head;
        while (tmp != null) {
            System.out.print(tmp.value + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }
    
}
