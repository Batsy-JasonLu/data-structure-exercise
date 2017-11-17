package com.lu.btree;

/**
 * 
 * @author lu
 * 
 * @description 设计一种直观的方式打印二叉树。
 *
 */
public class PrintBTreeDirectly {

    // 这里是把二叉树逆时针旋转90度打印，用右-根-左的遍历方式，统一每个节点的长度，并且每个节点前面加上层数个节点空格（即height*len）。
    public void printTree(TNode node, int height, String to, int len) {
        if (node == null) {
            return;
        }
        
        printTree(node.right, height + 1, "V", len);
        String val = to + node.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printTree(node.left, height + 1, "A", len);
    }
    
    public String getSpace(int num) {
        String space = " ";
        StringBuffer res = new StringBuffer("");
        
        for (int i = 0; i < num; i++) {
            res.append(space);
        }
        
        return res.toString();
    }
    
    /* 二叉树如图：     1
                /   \
               2     3
              / \   /
             4   5 6
    */
    public static void main(String[] args) {
        TNode node1 = new TNode(1);
        TNode node2 = new TNode(2);
        TNode node3 = new TNode(3);
        TNode node4 = new TNode(4);
        TNode node5 = new TNode(5);
        TNode node6 = new TNode(6);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        
        PrintBTreeDirectly main = new PrintBTreeDirectly();
        main.printTree(node1, 0, "H", 17);
    }
    
}
