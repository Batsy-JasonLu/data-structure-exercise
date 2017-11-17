package com.lu.btree;

/**
 * 
 * @author lu
 * 
 * @description 神级二叉树遍历（莫里斯遍历），是一种时间复杂度为o(n)，空间复杂度为o(1)的遍历，一般的递归和非递归遍历都用到了额外的数据结构，所以额外空间复杂度为o(h)，h大小由二叉树高度决定。
 *
 */
public class MorrisIterate {

    // 中序遍历最简单，用两个指针，一个是父节点，一个左子树，然后不断找左子树的右叶子节点（期间不断循环往下），并把它的right指针指向父节点，这样保证到该节点后能返回上层。
    // 当右叶子节点的right指针为空时，指向父节点并跳出当次循环；不为空时，先领它为空，然后出循环后，打印当前节点，接着父节点变成右子节点。
    public void morrisInOrder(TNode node) {
        if (node == null) {
            return;
        }
        
        TNode cur1 = node;
        TNode cur2 = null;
        
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            System.out.print(cur1.value + " ");
            cur1 = cur1.right;
        }
    }
    
    // 先序遍历的right指向连接父节点的操作，与中序相同；不同在于，当要连接父节点时，先打印父节点，另外在父节点的左子树为空时，打印该父节点（因为它现在已经是左叶子节点）。
    public void morrisPreOrder(TNode node) {
        if (node == null) {
            return;
        }
        
        TNode cur1 = node;
        TNode cur2 = null;
        
        while (cur1 != null) {
            cur2 = cur1.left;
            
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                
                if (cur2.right == null) {
                    cur2.right = cur1;
                    System.out.print(cur1.value + " ");
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            } else {
                System.out.print(cur1.value + " ");
            }
            cur1 = cur1.right;
        }
    }
    
    // 先序遍历的right指向连接父节点的操作，与中序相同；不同在于，是在每次去掉right指针指向父节点的时候，单打印左子节点（虽然调用了逆序打印，但是这里只有一个节点，所以只打印该节点），这样在循环内，能把左边的节点打印完，剩下的右边的节点，再调用一次逆序打印的方法即可。
    public void morrisPosOrder(TNode node) {
        if (node == null) {
            return;
        }
        
        TNode cur1 = node;
        TNode cur2 = null;
        
        while (cur1 != null) {
            cur2 = cur1.left;
            
            if (cur2 != null) {
                
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                    edgeIterate(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        edgeIterate(node);
    }
    
    public void edgeIterate(TNode node) {
        TNode tail = reverseTnode(node);
        TNode cur = tail;
        
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        
        reverseTnode(tail);
    }
    
    public TNode reverseTnode(TNode from) {
        TNode pre = null;
        TNode next = null;
        
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        
        return pre;
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

        MorrisIterate main = new MorrisIterate();
        main.morrisInOrder(node1);
        System.out.println();
        main.morrisPreOrder(node1);
        System.out.println();
        main.morrisPosOrder(node1);
    }

}
