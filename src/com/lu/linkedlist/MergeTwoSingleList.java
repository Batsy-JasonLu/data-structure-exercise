package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 合并两个有序链表，时间复杂度为o(n+m)，额外空间复杂度为o(0)。
 *
 */
public class MergeTwoSingleList {

    public Node mergeList(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            throw new RuntimeException();
        }
        
        Node cur1 = null;
        Node cur2 = null;
        Node head = null;
        Node pre = null;
        Node next = null;
        
        // 先特殊处理第一个节点，然后总是让cur1作为head较小的那一条链开始。
        head = head1.value < head2.value ? head1 : head2;
        if (head == head1) {
            cur1 = head1;
            cur2 = head2;
        } else {
            cur1 = head2;
            cur2 = head1;
        }
        
        pre = head;
        
        // 处理后续节点时，不能简单地用node=node.next去往后遍历，因为这种插入新元素的情况，用next往后会出错，正确的方式是每次遍历到的时候，先用一个node去保存当前位置的下一个节点，然后操作完后，再把当前节点去等于这个保存的节点，用这种方式向后遍历。
        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {
                pre = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                next = cur2.next;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        
        if (cur1 != null) {
            pre.next = cur1;
        } else {
            pre.next = cur2;
        }
        
        return head;
    }
    
    public static void main(String[] args) {
        Node node1 = new Node(1);
        node1.next = new Node(2);
        node1.next.next = new Node(4);
        node1.next.next.next = new Node(5);
        
        Node node2 = new Node(3);
        node2.next = new Node(6);
        node2.next.next = new Node(7);
        
        MergeTwoSingleList main = new MergeTwoSingleList();
        Node rNode = main.mergeList(node1, node2);
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
