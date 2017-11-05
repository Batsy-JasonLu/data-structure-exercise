package com.lu.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * 
 * @author lu
 * 
 * @description 给定一个二叉树结构，传入一个数组（值不重复），用一个方法把该数组变成一个maxtree，即头节点为最大值，子树都比父节点小，要求时间复杂度为o(n)。
 *
 */
public class MaxTreeByStack {

    public static Node[] node;
    
    public void buildMaxTree(int[] arr) {
        node = new Node[arr.length];
        
        // 先数组中的数变成一个个节点。
        for (int i = 0; i < node.length; i++) {
            node[i] = new Node(arr[i]);
        }
        
        Stack<Node> stack = new Stack<Node>();
        Node head = null;
        HashMap<Node, Node> lMaxNodeMap = new HashMap<Node, Node>();
        HashMap<Node, Node> rMaxNodeMap = new HashMap<Node, Node>();
        
        // 该方法从数组的左边到右边遍历，依次找出每个元素的左边第一个比它大的元素。
        for (int i = 0; i < node.length; i++) {
            Node curNode = node[i];
            
            while(!stack.isEmpty() && curNode.value > stack.peek().value) {
                popStack(stack, lMaxNodeMap);
            }
            stack.push(curNode);
        }
        
        while(!stack.isEmpty()) {
            popStack(stack, lMaxNodeMap);
        }
        
        // 用相同的方法，从数组的右边遍历，得到每个元素右边第一个比它大的元素。
        for (int i = node.length - 1; i > -1; i--) {
            Node curNode = node[i];
           
            while(!stack.isEmpty() && curNode.value > stack.peek().value) {
                popStack(stack, rMaxNodeMap);
            }
            stack.push(curNode);
        } 
        
        while(!stack.isEmpty()) {
            popStack(stack, rMaxNodeMap);
        }
        
        // 最后取每个元素的左右第一个比它大的元素出来进行比较，其中比较小的那个元素，就是当前元素的父节点，而当前元素是左/右子树，要看之前有没有左/右子树。
        for (int i = 0; i < node.length; i++) {
            Node curNode = node[i];
            
            Node lMaxNode = lMaxNodeMap.get(curNode);
            Node rMaxNode = rMaxNodeMap.get(curNode);
            
            if(lMaxNode == null && rMaxNode == null) {
                head = curNode;
            } else if (lMaxNode == null && rMaxNode != null) {
                if(rMaxNode.leftNode == null) {
                    rMaxNode.leftNode = curNode;
                } else {
                    rMaxNode.rightNode =curNode;
                }
            } else if (lMaxNode != null && rMaxNode == null) {
                if(lMaxNode.leftNode == null) {
                    lMaxNode.leftNode = curNode;
                } else {
                    lMaxNode.rightNode = curNode;
                }
            } else {
                Node parent = lMaxNode.value < rMaxNode.value ? lMaxNode : rMaxNode;
                if(parent.leftNode == null) {
                    parent.leftNode = curNode;
                } else {
                    parent.rightNode = curNode;
                }
            }
            
        }
       
    }
    
    // 用这个方法，保证该栈中，每一个元素的下面一个元素，都是左边/右边第一个比它大的元素。
    public void popStack(Stack<Node> stack, HashMap<Node, Node> map) {
        Node curNode = stack.pop();
        if(!stack.isEmpty()) {
            map.put(curNode, stack.peek());
        } else {
            map.put(curNode, null);
        }
    }
    
    public static void main(String[] args) {
        int arr[] = {3, 4, 5, 1, 2};
        MaxTreeByStack maxTreeByStack = new MaxTreeByStack();
        maxTreeByStack.buildMaxTree(arr);
        
        for (int i = 0; i < node.length; i++) {
            System.out.println("value is " + node[i].value);
            if(node[i].leftNode != null) {
                System.out.println("left node is " + node[i].leftNode.value);
            } 
            
            if(node[i].rightNode != null) {
                System.out.println("right node is " + node[i].rightNode.value);
            }
            System.out.println("---------------");
        }
    }

}


// 给定的二叉树结构
class Node {
    public int value;
    public Node leftNode;
    public Node rightNode;
    
    public Node(int value) {
        this.value = value;
    }
}
