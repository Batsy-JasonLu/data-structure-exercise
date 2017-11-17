package com.lu.btree;

/**
 * 
 * @author lu
 * 
 * @description 用两种标准，打印二叉树的边界节点，一是打印每一层最边上的节点；二是打印每一层最边上的节点，但是左边不打印右节点，右边不打印左节点。
 *
 */
public class PrintBtreeBorderNode {

    public void printBorderNode1(TNode node) {
        if (node == null) {
            throw new RuntimeException();
        }
        
        int height = getHeight(node);
        TNode[][] nodeMap = new TNode[height][2];
        
        setBorderMap(nodeMap, node, 0);
        
        // 打印左边的节点。
        for (int i = 0; i < height; i++) {
            System.out.print(nodeMap[i][0].value + " ");
        }
        
        // 打印非左右两边的叶子节点。
        printLeafNodeNotInMap(node, 0, nodeMap);
        
        // 打印右边的节点。
        for (int i = height - 1; i > -1; i--) {
            if (nodeMap[i][0] != nodeMap[i][1]) {
                System.out.print(nodeMap[i][1].value + " ");
            }
        }
        
    }
    
    public int getHeight(TNode head) {
        if (head == null) {
            return 0;
        }
        return Math.max(getHeight(head.left) + 1, getHeight(head.right) + 1);
    }
    
    public void setBorderMap(TNode[][] nodeMap, TNode node, int cur) {
        if (node == null) {
            return;
        }
        nodeMap[cur][0] = nodeMap[cur][0] == null ? node : nodeMap[cur][0];
        nodeMap[cur][1] = node;
        
        setBorderMap(nodeMap, node.left, cur + 1);
        setBorderMap(nodeMap, node.right, cur + 1);
    }
    
    public void printLeafNodeNotInMap(TNode node, int cur, TNode[][] nodeMap) {
        if (node == null) {
            return;
        }
        
        if (node.left == null && node.right == null && node != nodeMap[cur][0] && node != nodeMap[cur][1]) {
            System.out.print(node.value + " ");
        }
        
        printLeafNodeNotInMap(node.left, cur + 1, nodeMap);
        printLeafNodeNotInMap(node.right, cur + 1, nodeMap);
    }
    
    public void printBorderNode2(TNode node) {
        if (node == null) {
            return;
        }
        
        System.out.print(node.value + " ");
        
        if (node.left != null && node.right != null) {
            printLeftNode2(node.left, true);
            printRightNode2(node.right, true);
        } else {
            printBorderNode2(node.left != null ? node.left : node.right);
        }
        
    }

    public void printLeftNode2(TNode node, boolean print) {
        if (node == null) {
            return;
        }
        
        if (print || (node.left == null && node.right == null)) {
            System.out.print(node.value + " ");
        }
        
        printLeftNode2(node.left, print);
        printLeftNode2(node.right, print && node.left == null ? true : false);
    }
    
    public void printRightNode2(TNode node, boolean print) {
        if (node == null) {
            return;
        }
        
        printRightNode2(node.left, print && node.right == null ? true : false);
        printRightNode2(node.right, print);
        
        if (print || (node.left == null && node.right == null)) {
            System.out.print(node.value + " ");
        }
    }
    
    
    
    public static void main(String[] args) {
        TNode node1 = new TNode(1);
        TNode node2 = new TNode(2);
        TNode node3 = new TNode(3);
        TNode node4 = new TNode(4);
        TNode node5 = new TNode(5);
        TNode node6 = new TNode(6);
        TNode node7 = new TNode(7);
        TNode node8 = new TNode(8);
        TNode node9 = new TNode(9);
        TNode node10 = new TNode(10);
        TNode node11 = new TNode(11);
        TNode node12 = new TNode(12);
        TNode node13 = new TNode(13);
        TNode node14 = new TNode(14);
        TNode node15 = new TNode(15);
        TNode node16 = new TNode(16);
        
        node1.left = node2;
        node2.right = node4;
        node4.left = node7;
        node4.right = node8;
        node8.right = node11;
        node11.left = node13;
        node11.right = node14;
        
        node1.right = node3;
        node3.left = node5;
        node3.right = node6;
        node5.left = node9;
        node5.right = node10;
        node9.left = node12;
        node12.left = node15;
        node12.right = node16;
        
        PrintBtreeBorderNode main = new PrintBtreeBorderNode();
        main.printBorderNode1(node1);
        System.out.println();
        main.printBorderNode2(node1);
        
    }
    
}
