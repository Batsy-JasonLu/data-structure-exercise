package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 给定一个pivot值，将单向链表分成左边小，中间相等（可以没有相等的数），右边大的形式。进阶要求是，左右两边的链表顺序，与原链表的顺序相同。
 *
 */
public class PartitionList {

    public Node partition(Node head, int pivot) {
        Node node = head;
        int i = 0;
        while (node != null) {
            i++;
            node = node.next;
        }
        
        Node[] nodeArr = new Node[i];
        int j = 0;
        while (head != null) {
            nodeArr[j] = head;
            head = head.next;
            j++;
        }
        
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        // 如果index上的值大于pivot，则跟右边的值交换，并且index不变，然后再判断。
        // 当index的值小于pivot，用++small和index++的方式，让这个值不能，并且让small和index都后移一位。
        while (index != big) {
            if(nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == pivot) {
                index++;
            } else {
                swap(nodeArr, index, --big);
            }
        }
        
        for (int k = 0; k < nodeArr.length - 1; k++) {
            nodeArr[k].next = nodeArr[k+1];
        }
        nodeArr[nodeArr.length - 1].next = null;
        
        return nodeArr[0];
    }
    
    public void swap(Node[] arr, int a, int b) {
        Node tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
    
    public static void main(String[] args) {
        Node node = new Node(2);
        node.next = new Node(5);
        node.next.next = new Node(1);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(8);
        node.next.next.next.next.next = new Node(6);
        node.next.next.next.next.next.next = new Node(7);
        
        PartitionList main = new PartitionList();
        Node rNode = main.partition(node, 4);
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
