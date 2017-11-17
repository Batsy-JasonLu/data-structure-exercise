package com.lu.btree;

/**
 * 
 * @author lu
 * 
 * @description 在二叉树中找到任意一个节点的后继节点，普通的方法可以把二叉树中序遍历一遍，然后把节点带入，找到后继节点，这样的时间复杂度是o(n)。而下面的方法时间复杂度达到了o(l)，其中l代表两个节点之间的距离。
 *
 */
public class SearchBSTreeBehindNode {

    // 这种方法分3种情况。一是node有右子树，那么后继节点是右子树上最左的节点；二是没有右子树，如果node是node父节点的左子树，则node的父节点就是后继节点；三是以上情况都不是，就一直向上找，满足二的节点，找到后，它的父节点就是后继节点。
    public TNode2 searchBehindNode(TNode2 node) {
        if (node == null) {
            return null;
        }
        
        if (node.right != null) {
            return getMostLeftNode(node.right);
        }
        
        if (node == node.parent.left) {
            return node.parent;
        } else {
            while (node != null) {
                if (node == node.parent.left) {
                    return node.parent;
                }
                node = node.parent;
            }
        }
        
        return null;
    }
    
    public TNode2 getMostLeftNode(TNode2 node) {
        if (node == null) {
            return node;
        }
        
        while (node.left != null) {
            node = node.left;
        }
        
        return node;
    }
    
    /* 二叉树如图：        6
                /     \
               3       9
              / \     /  \
             1   4   8    10
              \   \ /
              2   5 7
    */
    public static void main(String[] args) {
        TNode2 node1 = new TNode2(1);
        TNode2 node2 = new TNode2(2);
        TNode2 node3 = new TNode2(3);
        TNode2 node4 = new TNode2(4);
        TNode2 node5 = new TNode2(5);
        TNode2 node6 = new TNode2(6);
        TNode2 node7 = new TNode2(7);
        TNode2 node8 = new TNode2(8);
        TNode2 node9 = new TNode2(9);
        TNode2 node10 = new TNode2(10);

        node6.left = node3;
        node6.right = node9;
        node3.left = node1;
        node3.right = node4;
        node1.right = node2;
        node4.right = node5;
        node9.left = node8;
        node9.right = node10;
        node8.left = node7;

        node6.parent = null;
        node3.parent = node6;
        node9.parent = node6;
        node1.parent = node3;
        node4.parent = node3;
        node2.parent = node1;
        node5.parent = node4;
        node8.parent = node9;
        node10.parent = node9;
        node7.parent = node8;
        
        
        SearchBSTreeBehindNode main = new SearchBSTreeBehindNode();
        System.out.println(main.searchBehindNode(node1).value);
        System.out.println(main.searchBehindNode(node2).value);
        System.out.println(main.searchBehindNode(node3).value);
        System.out.println(main.searchBehindNode(node4).value);
        System.out.println(main.searchBehindNode(node5).value);
        System.out.println(main.searchBehindNode(node6).value);
        System.out.println(main.searchBehindNode(node7).value);
        System.out.println(main.searchBehindNode(node8).value);
        System.out.println(main.searchBehindNode(node9).value);
    }

}

class TNode2 {
    
    public int value;
    public TNode2 left;
    public TNode2 right;
    public TNode2 parent;
    
    public TNode2(int value) {
        this.value = value;
    }
}