package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 把链表左右分半，然后用插空的方式，重新连成一个链表，即1-2-3-4-5-6变成1-4-2-5-3-6。
 *
 */
public class ListSplitAndCombine {

    public Node splitAndCombine(Node head) {
        if (head == null) {
            throw new RuntimeException();
        }
        
        Node node = head;
        int len = getLen(node);
        
        if (len < 2) {
            throw new RuntimeException();
        }
        
        int mid = len / 2;
        Node node2 = head;
        Node head1 = head;
        Node head2 = null;
        int i = 0;
        while (mid - 1 > i) {
            i++;
            node2 = node2.next;
        }
        head2 = node2.next;
        
        Node pre = null;
        Node next = null;
        
        // 这里和合并两个链表类似，都是要在原有链表中插入新元素，这时不能简单地用node.next往下遍历，而是要先用node保存当前节点的下一个节点，在操作完后，再令当前节点为保存的节点，用这种方式向后遍历。
        while (head1 != node2 && head2 != null) {
            pre = head1.next;
            next = head2.next;
            head1.next = head2;
            head2.next = pre;
            head1 = pre;
            head2 = next;
        }
        
        if (head2 != null) {
            head1.next = head2;
        }
        
        return head;
    }
    
    public int getLen(Node head) {
        int i = 0;
        while (head != null) {
            i++;
            head = head.next;
        }
        return i;
    }
    
    public static void main(String[] args) {
        Node node = new Node(2);
        node.next = new Node(5);
        node.next.next = new Node(1);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(8);
        node.next.next.next.next.next = new Node(6);
        node.next.next.next.next.next.next = new Node(7);
        
        ListSplitAndCombine main = new ListSplitAndCombine();
        Node rNode = main.splitAndCombine(node);
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
