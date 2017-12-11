package com.lu.btree;

import java.util.HashMap;

/**
 * 
 * @author lu
 * 
 * @description 利用先序、中序、后序，两两重建一棵二叉树。。。未通过测试！！！
 *
 */
public class RebuildBTreeByArray {

    // 前序的首元素为根，然后找到中序数组中的位置，左边的是左子树，右边是右子树。
    public TNode rebuildByPreAndIn(int[] arrPre, int[] arrIn) {
        if (arrPre == null || arrPre.length < 1 || arrIn == null || arrIn.length < 1) {
            return null;
        }
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arrIn.length; i++) {
            map.put(arrIn[i], i);
        }
        
        return preAndInIterate(arrPre, 0, arrPre.length - 1, arrIn, 0, arrIn.length - 1, map);
    }
    
    public TNode preAndInIterate(int[] arrPre, int preStart, int preStop, int[] arrIn, int inStart, int inStop, HashMap<Integer, Integer> map) {
        if (preStart > preStop) {
            return null;
        }
        
        TNode head = new TNode(arrPre[preStart]);
        int index = map.get(arrPre[preStart]);
        head.left = preAndInIterate(arrPre, preStart + 1, preStart + index - inStart, arrIn, inStart, index - 1, map);
        head.right = preAndInIterate(arrPre, preStart + index - inStart + 1, preStop, arrIn, index + 1, inStop, map);
        
        return head;
    }
    
    // 后序的最后一个元素是根，配合中序往前递归。
    public TNode rebuildByInAndPos(int[] arrIn, int[] arrPos) {
        if (arrIn == null || arrIn.length < 1 || arrPos == null || arrPos.length < 1) {
            return null;
        }
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < arrIn.length; i++) {
            map.put(arrIn[i], i);
        }
        
        return inPosIterate(arrIn, 0, arrIn.length - 1, arrPos, 0, arrPos.length - 1, map);
    }
    
    public TNode inPosIterate(int[] arrIn, int inStart, int inStop, int[] arrPos, int posStart, int posStop, HashMap<Integer, Integer> map) {
        if (posStart > posStop) {
            return null;
        }
        
        TNode head = new TNode(arrPos[inStop]);
        int index = map.get(arrPos[inStop]);
        head.left = inPosIterate(arrIn, inStart, index - 1, arrPos, posStart, posStart + index - inStart - 1, map);
        head.right = inPosIterate(arrIn, index + 1, inStop, arrPos, posStart + index - inStart, posStop - 1, map);
        
        return head;
    }
    
    // 要用后序和前序重建，必须要该二叉树除了叶子节点以外，其他的节点都有左右子树。
    public TNode rebuildByPreAndPos(int[] arrPre, int[] arrPos) {
        if (arrPre == null || arrPre.length < 1 || arrPos == null || arrPos.length < 1) {
            return null;
        }
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < arrPos.length; i++) {
            map.put(arrPos[i], i);
        }
        
        return prePosIterate(arrPre, 0, arrPre.length - 1, arrPos, 0, arrPos.length - 1, map);
    }

    public TNode prePosIterate(int[] arrPre, int preStart, int preStop, int[] arrPos, int posStart, int posStop, HashMap<Integer, Integer> map) {
        TNode head = new TNode(arrPos[posStop--]);
        
        if (preStart == posStop) {
            return head;
        }
        
        int index = map.get(arrPre[++preStart]);
        head.left = prePosIterate(arrPre, preStart, preStart + index - posStart, arrPos, posStart, index, map);
        head.right = prePosIterate(arrPre, preStart + index - posStart + 1, preStop, arrPos, index + 1, posStop, map);
        
        return head;
    }
    
    
    /*
                 1
                / \
               2   3
              / \ / \ 
             4  5 6  7
               / \
              8   9
     */
    public static void main(String[] args) {
        int[] arrPre = {1,2,4,5,8,9,3,6,7};
        int[] arrIn = {4,2,8,5,9,1,6,3,7};
        int[] arrPos = {4,8,9,5,2,6,7,3,1};
        
        RebuildBTreeByArray main = new RebuildBTreeByArray();
//        TNode n1 = main.rebuildByPreAndIn(arrPre, arrIn);
//        main.preOrderIterate(n1);
        TNode n2 = main.rebuildByInAndPos(arrIn, arrPos);
        main.preOrderIterate(n2);
//        TNode n3 = main.rebuildByPreAndPos(arrPre, arrPos);
//        main.preOrderIterate(n3);
    }
    
    public void preOrderIterate(TNode node) {
        if (node == null) {
            return;
        }
        
        System.out.print(node.value + " ");
        preOrderIterate(node.left);
        preOrderIterate(node.right);
    }
    
}
