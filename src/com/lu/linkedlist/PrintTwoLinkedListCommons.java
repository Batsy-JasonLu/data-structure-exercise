package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 给定两个有序链表的头指针，打印出公共部分。
 *
 */
public class PrintTwoLinkedListCommons {

    // value小的链表要往后找，当其中一个节点为null时停止。
    public void printLinkedListsCommons(Node head1, Node head2) {
        while (head1 != null && head2 != null) {
            if(head1.value == head2.value) {
                System.out.println(head1.value);
                head1 = head1.next;
                head2 = head2.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else if (head1.value < head2.value) {
                head1 = head1.next;
            }
        }
    }
    
    
    public static void main(String[] args) {
        Node node1 = new Node(1);
        node1.next = new Node(2);
        node1.next.next = new Node(3);
        node1.next.next.next = new Node(4);
        node1.next.next.next.next = new Node(5);
        
        Node node2 = new Node(2);
        node2.next = new Node(4);
        node2.next.next = new Node(6);
        
        PrintTwoLinkedListCommons printTwoLinkedListCommons = new PrintTwoLinkedListCommons();
        printTwoLinkedListCommons.printLinkedListsCommons(node1, node2);
    }

}

// 给定的邮箱链表的节点结构。
class Node {
    public int value;
    public Node next;
    
    public Node(int value) {
        this.value = value;
    }
}
