package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 反转单向和双向链表，要求时间复杂度为o(n)。
 *
 */
public class ReverseList {

    // 反转用到了两个辅助的指针，在左边的是pre，后边的是next（原顺序是从左到右）。
    // 反转的过程是，先把当前节点的next给辅助指针next，然后当前节点的指针重新指向左边（即前面，如果是头节点，则指向空）；之后pre和head都往右移一位，
    public Node reverseSingleList(Node head) {
        Node pre = null;
        Node next = null;
        
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    
    
    public static void main(String[] args) {
        Node node = new Node(2);
        node.next = new Node(5);
        node.next.next = new Node(1);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(8);
        node.next.next.next.next.next = new Node(6);
        node.next.next.next.next.next.next = new Node(7);
        
        ReverseList main = new ReverseList();
        Node rNode = main.reverseSingleList(node);
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
