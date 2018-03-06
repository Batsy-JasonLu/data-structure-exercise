package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 单链表的实现和操作。
 *
 */
public class LinkList {

    public Node current; // 当前节点
    public Node head; // 头节点

    public void add(int node) {
        if (head == null) {
            head = new Node(node);
            current = head;
        } else {
            current.next = new Node(node);
            current = current.next;
        }
    }

    public void addByIndex(int index, int data) {
        if (index < 0 || index > getLen() - 1) {
            return;
        }
        
        int pos = 0;
        current = head;
        while(pos != index) {
            pos = pos + 1;
            current = current.next;
        }
        
        Node node = new Node(data);
        node.next = current.next;
        current.next = node;
    }
    
    // 有pre指针时删除节点
    public void delByIndex(int index) {
        if (index < 0 || index > getLen() - 1) {
            return;
        }
        
        Node pre = head;
        Node cur = pre.next;
        int pos = 1;
        
        if(index == 0) {
            head = head.next;
        }
        
        while (cur != null) {
            if (pos == index) {
                break;
            } else {
                pos = pos + 1;
                pre = pre.next;
                cur = cur.next;
            }
        }
        
        Node node = pre.next;
        pre.next = node.next;
        node = null;
    }
    
    public int getLen() {
        int sum = 0;
        
        current = head;
        while(current != null) {
            sum = sum + 1;
            current = current.next;
        }
        
        return sum;
    }
    
    public void printByindex(int index) {
        if (index < 0 || index > getLen() - 1) {
            return;
        }
        
        int pos = 0;
        current = head;
        while(pos != index) {
            pos = pos + 1;
            current = current.next;
        }
        
        System.out.print(current.value + " ");
        
        while (current != null) {
            System.out.print(current.value);
            current = current.next;
        }
        System.out.println();
    }
    
    public void printByData(int data) {
        current = head;
        while(current.value != data) {
            current = current.next;
        }
        
        System.out.print(current.value + " ");
        
        while (current != null) {
            System.out.print(current.value);
            current = current.next;
        }
        System.out.println();
    }
    
    public void searchNoIterate(int indexDesc) {
        Node first = null;
        Node second = null;
        
        if (indexDesc == 0) {
            return;
        } else {
            first = head;
            second = head;
            
            for(int i=0; i<indexDesc; i++) {
                second = second.next;
            }
            
            while (second != null) {
                second = second.next;
                first = first.next;
            }
            
            System.out.println(first.value);
        }
    }
    
    public void searchMidNoIterate() {
        Node first = head;
        Node second = head;
        
        while(second != null && second.next != null) {
            second = second.next.next;
            first = first.next;
        }
        System.out.println(first.value);
    }
    
    public Node ReverseIteratively(Node head) {
        Node pReversedHead = head;
        Node pNode = head;
        Node pPrev = null;
        
        while (pNode != null) {
            Node pNext = pNode.next;
            if (pNext == null) {
                pReversedHead = pNode;
            }
            pNode.next = pPrev;
            pPrev = pNode;
            pNode = pNext;
        }
        
        this.head = pReversedHead;
        return this.head;
    }
    
    
    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        linkList.add(1);
        linkList.add(2);
        linkList.add(3);
        linkList.add(5);
        linkList.add(6);
        linkList.add(7);
        linkList.add(9);

        linkList.printList();
//        System.out.println(linkList.getLen());
        linkList.printByindex(0);
        linkList.printByData(7);
//          
//        linkList.addByIndex(2, 8);
//        linkList.printList();
        
//        linkList.delByIndex(2);
//        linkList.printList();
        
//        linkList.searchNoIterate(5);
//        linkList.searchMidNoIterate();
//        
//        
//        linkList.ReverseIteratively(linkList.head);
//        linkList.printList();
    }
    
    public void printList() {
        current = head;
        while (current != null) {
            System.out.print(current.value);
            current = current.next;
        }
        System.out.println();
    }
}

// 给定的单链表的节点结构。
class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }
}