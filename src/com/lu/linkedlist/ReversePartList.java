package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 反转单链表中的一部分。
 *
 */
public class ReversePartList {

    public Node reverseList(Node head, int from, int to) {
        Node preNode = null;
        Node posNode = null;
        Node node = head;
        int i = 1;
        
        while (node.next != null) {
            if (i == from - 1) {
                preNode = node;
            }
            
            if (i == to + 1) {
                posNode = node;
            }
            
            i++;
            node = node.next;
        }
        
        // 如果有preNode，说明原头节点没有被反转；否则说明原头节点被反转。
        // 然后先把要反转的第一个节点的next指向posNode，然后从要反转的第二个节点开始，像之前反转链表一样操作（之前反转链表时的node1其实就是null）。
        Node node1 = preNode != null ? preNode.next : head;
        Node node2 = node1.next;
        node1.next = posNode;
        Node next = null;
        
        while (node2 != posNode) {
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }
        
        if (preNode != null) {
            preNode.next = node1;
            return head;
        } else {
            return node1;
        }
        
    }
    
    public static void main(String[] args) {
        Node node = new Node(2);
        node.next = new Node(5);
        node.next.next = new Node(1);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(8);
        node.next.next.next.next.next = new Node(6);
        node.next.next.next.next.next.next = new Node(7);
        
        ReversePartList main = new ReversePartList();
        Node rNode = main.reverseList(node, 1, 7);
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
