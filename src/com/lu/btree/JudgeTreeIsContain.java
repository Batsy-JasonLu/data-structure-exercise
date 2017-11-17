package com.lu.btree;

/**
 * 
 * @author lu
 * 
 * @description 判断二叉树t1中是否包含t2的所有拓扑结构。
 *
 */
public class JudgeTreeIsContain {

    public boolean isContain(TNode node1, TNode node2) {
        return check(node1, node2) || isContain(node1.left, node2) || isContain(node1.right, node2);
    }
    
    public boolean check(TNode node1, TNode node2) {
        if (node2 == null) {
            return true;
        }
        
        if (node1 == null || node1.value != node2.value) {
            return false;
        }
        
        return check(node1.left, node2.left) && check(node1.right, node2.right);
    }
    
    /* 二叉树如图：      1
                /    \
               2      3
                \    /  \
                 5  6    7
                   /    / \  
                  8    10  11
                  
                  
                  3
                 / \
                6   7
                   /
                  10
    */
    public static void main(String[] args) {
        TNode node1 = new TNode(1);
        TNode node2 = new TNode(2);
        TNode node3 = new TNode(3);
        TNode node5 = new TNode(5);
        TNode node6 = new TNode(6);
        TNode node7 = new TNode(7);
        TNode node8 = new TNode(8);
        TNode node10 = new TNode(10);
        TNode node11 = new TNode(11);

        node1.left = node2;
        node1.right = node3;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node6.left = node8;
        node7.left = node10;
        node7.right = node11;
        
        TNode node2_3 = new TNode(3);
        TNode node2_6 = new TNode(6);
        TNode node2_7 = new TNode(7);
        TNode node2_10 = new TNode(10);
        
        node2_3.left = node2_6;
        node2_3.right = node2_7;
        node2_7.left = node2_10;

        JudgeTreeIsContain main = new JudgeTreeIsContain();
        boolean res = main.isContain(node1, node2_3);
        System.out.println(res);
    }

}
