package com.lu.linkedlist;

/**
 * 
 * @author lu
 *
 * @description 删除链表中间的节点和a/b处的节点。
 *
 */
public class DeleteListMidOrAbNode {

    public void deleteMidNode(Node head) {
        if(head == null) {
            throw new RuntimeException();
        }
        
        int len = getLen(head);
        
        if(len < 2) {
            throw new RuntimeException();
        }
        
        int mid = (int) Math.ceil(len/2);
        int i = 0;
        
        // 一定要记住，链表删除节点的操作，一定要停在删除节点的前一个节点。
        while (i < mid - 1) {
            i++;
            head = head.next;
        }
        
        head.next = head.next.next;
    }
    
    public void deleteAbNode(Node head, int a, int b) {
        double num = ((double) a) / ((double) b);
        int res = (int) Math.ceil(num * getLen(head));
        int i = 0;
        
        // 一定要记住，链表删除节点的操作，一定要停在删除节点的前一个节点。
        while (i < res - 1) {
            i++;
            head = head.next;
        }
        
        head.next = head.next.next;
    }
    
    public int getLen(Node head) {
        int i = 1;
        while (head.next != null) {
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
        
        DeleteListMidOrAbNode main = new DeleteListMidOrAbNode();
//        main.deleteMidNode(node);
//        main.printList(node);
        
        main.deleteAbNode(node, 4, 5);
        main.printList(node);
        
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
