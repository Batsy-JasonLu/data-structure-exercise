package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 两个单链表转成整数相加，得到一个整数，把这个整数拆分成多个个位数，最后把数字连成链表。
 *
 */
public class TwoSingleListAddMerge {

    public Node addSplitMerge(Node head1, Node head2) {
        int num1 = getLen(head1) - 1;
        int sum1 = 0;
        Node node1 = head1;
        while (node1 != null) {
            sum1 = (int) (sum1 + node1.value*Math.pow(10, num1));
            num1--;
            node1 = node1.next;
        }

        int num2 = getLen(head2) - 1;
        int sum2 = 0;
        Node node2 = head2;
        while (node2 != null) {
            sum2 = (int) (sum2 + node2.value*Math.pow(10, num2));
            num2--;
            node2 = node2.next;
        }
        
        int i = 0;
        int res = sum1 + sum2;
        while (res > 10) {
            res = res / 10;
            i++;
        }
        
        int sum = sum1 + sum2;
        Node[] node = new Node[i+1];
        int num = node.length - 1;
        for (int j = 0; j < node.length; j++) {
            node[j] = new Node((int) (sum / Math.pow(10, num)));
            sum = (int) (sum - node[j].value * Math.pow(10, num));
            num--;
        }
        
        for (int j = 0; j < node.length - 1; j++) {
            node[j].next = node[j+1];
        }
        node[node.length - 1].next = null;
        
        return node[0];
    }
    
    public int getLen(Node node) {
        int i = 0;
        while (node != null) {
            node = node.next;
            i++;
        }
        return i;
    }
    
    public static void main(String[] args) {
        Node node1 = new Node(2);
        node1.next = new Node(5);
        node1.next.next = new Node(1);
        node1.next.next.next = new Node(4);
        
        Node node2 = new Node(7);
        node2.next = new Node(6);
        node2.next.next = new Node(3);
        
        TwoSingleListAddMerge main = new TwoSingleListAddMerge();
        Node rNode = main.addSplitMerge(node1, node2);
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
