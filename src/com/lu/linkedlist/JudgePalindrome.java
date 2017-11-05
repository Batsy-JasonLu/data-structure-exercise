package com.lu.linkedlist;

import java.util.Stack;

/**
 * 
 * @author lu
 * 
 * @description 判断链表是否回文（回文即正顺和倒序都一样），进阶要求是时间复杂度为o(n)，额外空间复杂度为o(1)。
 *
 */
public class JudgePalindrome {

    public boolean isPalindrome(Node head) {
        Stack<Integer> stack = new Stack<Integer>();
        Node node = head;
        
        while (head != null) {
            stack.push(head.value);
            head = head.next;
        }
        
        while (node != null && !stack.isEmpty()) {
            if (node.value != stack.peek()) {
                return false;
            }
            node = node.next;
            stack.pop();
        }
        return true;
    }
    
    public boolean isPalindrome2(Node head) {
        Stack<Integer> stack = new Stack<Integer>();
        Node node = head;
        
        Node cur = head;
        Node right = head;
        // 两个指针，一个走两步，一个走一步，使后面的指针停在中间。
        while (cur.next != null && cur.next.next != null) {
            cur = cur.next.next;
            right = right.next;
        }
        
        System.out.println(cur.value);
        System.out.println(right.value);
        
        while (right != null) {
            stack.push(right.value);
            right = right.next;
        }
        
        while (!stack.isEmpty()) {
            if (node.value != stack.peek()) {
                return false;
            }
            node = node.next;
            stack.pop();
        }
        return true;
    }
    
    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(2);
        node.next.next.next.next = new Node(1);
        
        JudgePalindrome main = new JudgePalindrome();
        boolean res = main.isPalindrome2(node);
        System.out.println(res);
    }

    public void printStack(Stack<Integer> stack) {
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
