package com.lu.linkedlist;

import java.util.HashMap;

/**
 * 
 * @author lu
 * 
 * @description 复制含有随机指针的特殊链表。进阶要求是，不使用额外的数据结构。
 *
 */
public class CopyWithSpecialPointerList {

    public SpecialNode copy(SpecialNode head) {
        HashMap<SpecialNode, SpecialNode> map = new HashMap<SpecialNode, SpecialNode>();
        SpecialNode node = head;
        SpecialNode first = head;
        
        while (head != null) {
            map.put(head, new SpecialNode(head.value));
            head = head.next;
        }
        
        while (node != null) {
            map.get(node).next = node.next;
            map.get(node).rand = node.rand;
            node = node.next;
        }
        
        return map.get(first);
    }
    
    public static void main(String[] args) {
        SpecialNode node1 = new SpecialNode(1);
        SpecialNode node2 = new SpecialNode(2);
        SpecialNode node3 = new SpecialNode(3);
        SpecialNode node4 = new SpecialNode(4);
        
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        
        node1.rand = node4;
        node2.rand = node3;
        node3.rand = node1;
        node4.rand = node2;
        
        CopyWithSpecialPointerList main = new CopyWithSpecialPointerList();
        main.print(node1);
        SpecialNode rNode = main.copy(node1);
        main.print(rNode);
        
    }

    public void print(SpecialNode node) {
        while (node != null) {
            if (node.next == null) {
                System.out.println(node.value + ": " + " " + "," + node.rand.value);
            } else {
                System.out.println(node.value + ": " + node.next.value + "," + node.rand.value);
            }
            node = node.next;
        }
        System.out.println();
    }
    
}

// 给定的有特殊指针的节点结构。
class SpecialNode {
    public int value;
    public SpecialNode next;
    public SpecialNode rand;
    
    public SpecialNode(int value) {
        this.value = value;
    }
}
