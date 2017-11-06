package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 将一个单链表，每k个节点进行逆序。
 *
 */
public class ReverseListEveryKPart {

    public Node reverseList(Node head, int k) {
        if (head == null || k < 2) {
            throw new RuntimeException();
        }
        
        Node cur = head;
        Node start = null;
        Node pre = null;
        Node next = null;
        int count = 1;
        while (cur != null) {
            next = cur.next;
            while (count == k) {
                start = pre == null ? head : pre.next; // 这里的判断主要是为了把第一组和其他组分开处理。
                head = pre == null ? cur : head;
                reverse(pre, start, cur, next);
                pre = start; // 在经过上一步的逆序后，start节点会由上一部分的第一个节点变成最后一个节点，而这也成为下一部分的第一个节点的前一个节点。
                count = 0;
            }
            count++;
            cur = next;
        }
        
        return head;
    }
    
    // start和end是要逆序的开头和结尾，left和right是开头和结尾的前/后一个节点，用来逆序完后重新串连链表。
    public void reverse(Node left, Node start, Node end, Node right) {
        Node pre = start;
        Node cur = start.next;
        Node next = null;
        
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        
        if (left != null) {
            left.next = end;
        }
        start.next = right;
    }
    
    public static void main(String[] args) {
        Node node = new Node(2);
        node.next = new Node(5);
        node.next.next = new Node(1);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(8);
        node.next.next.next.next.next = new Node(6);
        node.next.next.next.next.next.next = new Node(7);
        
        ReverseListEveryKPart main = new ReverseListEveryKPart();
        Node rNode = main.reverseList(node, 3);
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
