package com.lu.linkedlist;

/**
 * 
 * @author lu
 * 
 * @description 删除单链表或者双链表的倒数第K个节点，要求时间复杂度为o(n)。注意，要统一正向和倒向时的起始都为0或者1。
 *
 */
public class DeleteListDescKNode {

    // 这里使用的方法与书上的不同，是用了两个指针，一个先遍历，另一个等着，等之间的距离为k+1时（k为间隔距离，再加1让后一个指针停在要删除的节点前面），另一个指针开始走，当第一个指针到末尾时，第二个指针刚好到k的前一位。
    // while的停止条件是node.next != null，而不是node != null，一定要注意。
    // 要注意i和k的边界条件，是<不是<=，记住这个分两步while的写法！
    public void deleteSingleListDescKNode(Node head, int k) {
        if (head == null || k <= 0) {
            throw new RuntimeException("print error");
        }
        
        Node p1 = head;
        Node p2 = head;
        int i = 1;
        int j = 1;
        
        while (p1.next != null && i < k + 1) {
            i++;
            p1 = p1.next;
        }
        
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
            j++;
        }
        
        // j==1时是删除当前节点，其实上是把当前节点变成后面那个节点（把值变成一样），然后删除后面那个节点。
        if (j == 1) {
            Node node = p2.next;
            p2.value = node.value;
            p2.next = node.next;
        } else {
            Node del = p2.next;
            p2.next = p2.next.next;
        }
        
    }
    
    public void printList(Node head) {
        Node tmp = head;
        while (tmp != null) {
            System.out.print(tmp.value + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Node node = new Node(2);
        node.next = new Node(5);
        node.next.next = new Node(1);
        node.next.next.next = new Node(4);
        node.next.next.next.next = new Node(8);
        node.next.next.next.next.next = new Node(6);
        node.next.next.next.next.next.next = new Node(7);
        
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        
        DeleteListDescKNode deleteListDescKNode = new DeleteListDescKNode();
        deleteListDescKNode.deleteSingleListDescKNode(node, 1);
        
        deleteListDescKNode.printList(node);
    }

}

class DoubleNode {
    public int value;
    public Node pre;
    public Node next;
    
    public DoubleNode(int value) {
        this.value = value;
    }
}

