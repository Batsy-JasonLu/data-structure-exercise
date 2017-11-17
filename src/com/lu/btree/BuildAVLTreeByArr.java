package com.lu.btree;

/**
 * 
 * @author lu
 * 
 * @description 把一个有序数组生成一个平衡二叉搜索树，且其中序遍历与数组的顺序相同。
 *
 */
public class BuildAVLTreeByArr {

    // 要用有序数组生成avl树，其数组中间的数就是头节点，然后不断递归左边的数构成左子树，右边是右子树。
    public TNode buildAVLTree(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        
        return buildTree(arr, 0, arr.length - 1); 
    }
    
    public TNode buildTree(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        
        int mid = (start + end) / 2;
        TNode head = new TNode(arr[mid]);
        head.left = buildTree(arr, start, mid - 1);
        head.right = buildTree(arr, mid + 1, end);
        
        return head;
    }
    
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        BuildAVLTreeByArr main = new BuildAVLTreeByArr();
        TNode res = main.buildAVLTree(arr);
        main.inOrderIterate(res);
    }
    
    public void inOrderIterate(TNode node) {
        if (node == null) {
            return;
        }
        
        inOrderIterate(node.left);
        System.out.print(node.value + " ");
        inOrderIterate(node.right);
    }
    
}
