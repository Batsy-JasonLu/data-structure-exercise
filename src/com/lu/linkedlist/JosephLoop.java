package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 约瑟夫问题，一个单向环链表，从第一个开始节点，每次到第N个节点就删除该节点，然后下一个节点再重新从一开始算，这样一直到只有一个节点为止，返回这个节点。常规解法o(m*n)，即遍历次数*节点数，进阶解法是o(n)，即节点数。
 *
 */
public class JosephLoop {
    
    public Node deleteJosephLoopNode(Node head, int m) {
        if (head == null && m <= 0) {
            throw new RuntimeException();
        }
        
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }
        
        int count = 0;
        while (last != head) {
            count++;
            if (count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = last.next;
        }
        
        return head;
    }

    public static void main(String[] args) {
        Node node = new Node(2);
        node.next = new Node(5);
        node.next.next = new Node(1);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(8);
        node.next.next.next.next.next = new Node(6);
        node.next.next.next.next.next.next = new Node(7);
        node.next.next.next.next.next.next.next = node;
        
        JosephLoop main = new JosephLoop();
        Node rNode = main.deleteJosephLoopNode(node, 3);
        System.out.println(rNode.value);
    }
    
}
