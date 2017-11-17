package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 向有序的环形链表中插入新的节点。要求时间复杂度为o(n)，空间复杂度为o(1)。
 *
 */
public class InsertNodeToOrderLoopList {

    public Node insertNode(Node head, int num) {
        if (head == null) {
            throw new RuntimeException();
        }
        
        Node pre = head;
        Node cur = head.next;
        Node tail = head.next;
        Node node = new Node(num);
        while (tail.next != head) {
            tail = tail.next;
        }
        
        
        if (num < pre.value) {
            tail.next = node;
            node.next = head;
            head = node;
        } else {
            while (cur != head) {
                if (num < cur.value) {
                    break; // 当遇到插入的点时，跳出循环，然后再去插入节点；插入完后不能再循环，否则会出错。
                }
                pre = cur;
                cur = cur.next;
            }
            pre.next = node;
            node.next = cur;
        }
        
        return head;
    }
    
    public static void main(String[] args) {
        Node node = new Node(2);
        node.next = new Node(3);
        node.next.next = new Node(6);
        node.next.next.next = new Node(8);
        node.next.next.next.next = new Node(9);
        node.next.next.next.next.next = node;
        
        InsertNodeToOrderLoopList main = new InsertNodeToOrderLoopList();
        Node rNode = main.insertNode(node, 5);
        main.printList(rNode);
    }
    
    public void printList(Node head) {
        System.out.print(head.value + " ");
        Node tmp = head.next;
        while (tmp != head) {
            System.out.print(tmp.value + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }
    
}
