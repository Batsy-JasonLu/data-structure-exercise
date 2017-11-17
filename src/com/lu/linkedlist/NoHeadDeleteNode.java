package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 没有头节点时，如何删除当前节点；删除的方法有什么问题。
 *
 */
public class NoHeadDeleteNode {

    public Node deleteNode(Node node) {
        if (node == null) {
            throw new RuntimeException();
        }
        
        Node next = node.next;
        if (next == null) {
            throw new RuntimeException();
        }
        
        // 用这种方式，实际上是拷贝了后一个节点的值赋给当前节点，然后删除后面的节点。
        // 问题一，是无法删除尾节点，因为不能简单地令node=null，null在系统中是一个特定的区域。
        // 问题二，这样的本质是删除了后一个节点，改变了节点的结构，有时遇到节点的value很复杂的情况，很可能出现问题。
        node.value = next.value;
        node.next = next.next;
        
        return node;
    }
    
    public static void main(String[] args) {
        Node node = new Node(2);
        node.next = new Node(5);
        node.next.next = new Node(1);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(8);
        node.next.next.next.next.next = new Node(6);
        node.next.next.next.next.next.next = new Node(7);
        
        NoHeadDeleteNode main = new NoHeadDeleteNode();
        Node rNode = main.deleteNode(node);
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
