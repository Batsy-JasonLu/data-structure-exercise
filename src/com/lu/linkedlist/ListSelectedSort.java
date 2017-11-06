package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 对无序链表选择排序，额外空间复杂度为o(1)，即不能把它放进数组排序后再连起来。
 *
 */
public class ListSelectedSort {

    public Node selectedSort(Node head) {
        if (head == null) {
            throw new RuntimeException();
        }
        
        Node cur = head;
        Node tail = null;
        Node small = null;
        Node smallPre = null;
        
        while (cur != null) {
            small = cur;
            smallPre = getSmallestPre(cur);
            
            // smallPre不为空，则说明有更小的值；这时取到small前面的节点，通过操作（用smallpre把small节点删去），把小的节点连到新的small链表上。
            if (smallPre != null) {
                small = smallPre.next;
                smallPre.next = small.next;
            }
            
            // 如果当前数已是最小，则cur往下走；否则继续用它去找最小值。
            cur = cur == small ? cur.next : cur;
            
            // 然后tail为空时，说明是第一个节点，则赋为最小，并保存到head；否则都用next去连接最小值，然后tail后移一位。
            if (tail == null) {
                tail = small;
                head = tail;
            } else {
                tail.next = small;
            }
            tail = small;
        }
        
        return head;
    }
    
    // 从当前位置开始遍历，找到最小的值并返回，如果没有比当前位置值更小的值，则返回null。
    public Node getSmallestPre(Node head) {
        Node smallPre = null;
        Node small = head;
        Node pre = head;
        Node cur = head.next;
        
        while (cur != null) {
            if (cur.value < small.value) {
                smallPre = pre;
                small = cur;
            }
            pre = cur;
            cur = cur.next;
        }
        return smallPre;
    }
    
    public static void main(String[] args) {
        Node node = new Node(2);
        node.next = new Node(5);
        node.next.next = new Node(1);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(8);
        node.next.next.next.next.next = new Node(6);
        node.next.next.next.next.next.next = new Node(7);
        
        ListSelectedSort main = new ListSelectedSort();
        Node rNode = main.selectedSort(node);
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
