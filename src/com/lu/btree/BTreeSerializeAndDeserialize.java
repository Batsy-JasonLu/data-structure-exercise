package com.lu.btree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author lu
 * 
 * @description 二叉树的序列化和反序列化（序列化即把node转为字符串，反序列化是把字符串转回node），分别用先序和层序实现。
 *
 */
public class BTreeSerializeAndDeserialize {

    public String serializeByPreOrder(TNode node) {
        if (node == null) {
            return "#!";
        }
        
        String res = node.value + "!";
        res += serializeByPreOrder(node.left);
        res += serializeByPreOrder(node.right);
        
        return res;
    }
    
    public TNode deserializeByPreOrder(String file) {
        String[] fileArr = file.split("!");
        Queue<String> queue = new LinkedList<String>();
        
        for (int i = 0; i < fileArr.length; i++) {
            queue.offer(fileArr[i]);
        }
        
        return deserialize(queue);
    }
    
    public TNode deserialize(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        
        TNode head = new TNode(Integer.valueOf(value));
        head.left = deserialize(queue);
        head.right = deserialize(queue);
        
        return head;
    }
    
    
    public String serializeByLevelOrder(TNode node) {
        if (node == null) {
            return "#!";
        }
        
        String res = node.value + "!";
        Queue<TNode> queue = new LinkedList<TNode>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            TNode cur = queue.poll();
            
            if (cur.left != null) {
                res += cur.left.value + "!";
                queue.offer(cur.left);
            } else {
                res += "#!";
            }
            
            if (cur.right != null) {
                res += cur.right.value + "!";
                queue.offer(cur.right);
            } else {
                res += "#!";
            }
        }
        
        return res;
    }
    
    public TNode deserializeByLevelOrder(String file) {
        String[] fileArr = file.split("!");
        int index = 0;
        
        Queue<TNode> queue = new LinkedList<TNode>();
        TNode head = generateTNode(fileArr[index++]);
        queue.offer(head);
        TNode node = null;
        
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateTNode(fileArr[index++]);
            node.right = generateTNode(fileArr[index++]);
            
            if (node.left != null) {
                queue.offer(node.left);
            }
            
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        
        return head;
    }
    
    public TNode generateTNode(String file) {
        if (file.equals("#")) {
            return null;
        }
        
        return new TNode(Integer.valueOf(file));
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

        BTreeSerializeAndDeserialize main = new BTreeSerializeAndDeserialize();
        String file1 = main.serializeByPreOrder(node1);
        System.out.println(file1);
        TNode rNode1 = main.deserializeByPreOrder(file1);
        main.preOrderIterate(rNode1);
        
        System.out.println();
        
        String file2 = main.serializeByLevelOrder(node1);
        System.out.println(file2);
        TNode rNode2 = main.deserializeByLevelOrder(file2);
        main.preOrderIterate(rNode2);
        
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
