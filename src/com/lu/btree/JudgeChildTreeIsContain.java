package com.lu.btree;

import com.lu.other.KMP;

/**
 * 
 * @author lu
 * 
 * @descritption 判断t1树中是否有与t2树完全相同的拓扑结构的子树。
 *
 */
public class JudgeChildTreeIsContain {

    // 这里把两个二叉树序列化为字符串，然后问题相当于变成了，看一个字符串是否是另一个字符串的子串，这个问题可以用kmp算法解决，让时间复杂度达到O(n)。
    // 把二叉树序列化后在比较和操作，是解决树和子树的一种思路。
    public boolean isContain(TNode node1, TNode node2) {
        String res1 = serialize(node1);
        String res2 = serialize(node2);
        
        int index = KMP.kmpSearch(res1, res2);
        
        return index == -1 ? false : true;
    }
    
    public String serialize(TNode node) {
        if (node == null) {
            return "#!";
        }
        
        String res = node.value + "!";
        res += serialize(node.left);
        res += serialize(node.right);
        
        return res;
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
               /   /
              8   10
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
        TNode node2_8 = new TNode(8);
        TNode node2_10 = new TNode(10);
        TNode node2_11 = new TNode(11);

        node2_3.left = node2_6;
        node2_3.right = node2_7;
        node2_6.left = node2_8;
        node2_7.left = node2_10;
        // 有就是完整子串，没有则不是。
        node2_7.right = node2_11;

        JudgeChildTreeIsContain main = new JudgeChildTreeIsContain();
        boolean res = main.isContain(node1, node2_3);
        System.out.println(res);
    }

}
