package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 删除链表中的指定节点，头节点不删除。
 *
 */
public class DeleteListAppointNode {

    public Node deleteAppointNode(Node head, int k) {
        if (head == null) {
            throw new RuntimeException();
        }
        
        Node cur = head;
        Node pre = head;
        
        while (cur != null) {
            if (cur.value == k) {
                pre.next = cur.next;
            } else {
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
        
        DeleteListAppointNode main = new DeleteListAppointNode();
        Node rNode = main.deleteAppointNode(node, 1);
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
