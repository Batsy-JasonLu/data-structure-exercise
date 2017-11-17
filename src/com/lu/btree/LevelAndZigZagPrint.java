package com.lu.btree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author lu
 * 
 * @description 二叉树实现特殊的层级打印和zigzag打印。
 *
 */
public class LevelAndZigZagPrint {

    public void levelPrint(TNode node) {
        if (node == null) {
            return;
        }
        
        int level = 1;
        TNode last = node;
        TNode nLast = null;
        Queue<TNode> queue = new LinkedList<TNode>();
        
        queue.offer(node);
        System.out.print("level_" + level + " : ");
        while (!queue.isEmpty()) {
            TNode cur = queue.poll();
            System.out.print(cur.value + " ");
            
            if (cur.left != null) {
                queue.offer(cur.left);
                nLast = cur.left;
            }
            
            if (cur.right != null) {
                queue.offer(cur.right);
                nLast = cur.right;
            }
            
            // 当队列出来的节点等于last时，说明到了最右边，此时换行，并新的最右节点nlast赋给last。
            if (cur.value == last.value && !queue.isEmpty()) {
                System.out.println();
                System.out.print("level_" + ++level + " : ");
                last = nLast;
            }
        }
    }
    
    // 当用非递归操作二叉树时，循环的条件都为queue/stack不为空，然后if当前节点的左/右子树是否为空，并添加进来。
    // 在这里，zigzag是呈蛇形打印，当前为从左到右时，下层为从右到左，关键点在于换行；用last节点去保存出队列的节点的最边界子节点，用双端队列，从左到右遍历时，就从左到右offerlast，相反，就offerfirst。
    public void ziazagPrint(TNode node) {
        if (node == null) {
            return;
        }
        
        int level = 1;
        TNode last = node;
        TNode nLast = null;
        TNode cur = null;
        
        LinkedList<TNode> queue = new LinkedList<TNode>();
        queue.offer(node);
        System.out.print("level_" + level + " : ");
        
        while (!queue.isEmpty()) {
            if (level % 2 != 0) {
                cur = queue.pollFirst();
                
                if (cur.left != null) {
                    nLast = nLast == null ? cur.left : nLast;
                    queue.offerLast(cur.left);
                }
                
                if (cur.right != null) {
                    nLast = nLast == null ? cur.right : nLast;
                    queue.offerLast(cur.right);
                }
                
            } else {
                cur = queue.pollLast();
                
                if (cur.right != null) {
                    nLast = nLast == null ? cur.right : nLast;
                    queue.offerFirst(cur.right);
                }
                
                if (cur.left != null) {
                    nLast = nLast == null ? cur.left : nLast;
                    queue.offerFirst(cur.left);
                }
            }
            
            System.out.print(cur.value + " ");
            
            if (cur.value == last.value && !queue.isEmpty()) {
                last = nLast;
                nLast = null;
                System.out.println();
                System.out.print("level_" + ++level + " : ");
            }
            
        }
        
    }
    
    /* 二叉树如图：      1
                /    \
               2      3
                \    /  \
                 5  6    7
                   /    / \  
                  8    10  11
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

        LevelAndZigZagPrint main = new LevelAndZigZagPrint();
        main.levelPrint(node1);
        System.out.println();
        main.ziazagPrint(node1);
    }

}
