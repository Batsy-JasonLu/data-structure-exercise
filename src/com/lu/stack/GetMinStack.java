package com.lu.stack;

import java.util.Stack;

import javax.swing.Popup;

/**
 * 
 * @author lu
 * 
 * @description 实现一个栈，除了正常功能，还能随时返回栈中的最小值，复杂度都为O(1)
 *
 */
public class GetMinStack {

    private Stack<Integer> stack;
    private Stack<Integer> stackMin;
    
    private GetMinStack() {
        stack = new Stack<Integer>();
        stackMin = new Stack<Integer>();
    }
    
    public void push(int num) {
        stack.push(num);
        if(stackMin.isEmpty()) {
            stackMin.push(num);
        } else {
            if(stackMin.peek() < num) {
                stackMin.push(stackMin.peek());
            } else {
                stackMin.push(num);
            }
        }
    }
    
    public int pop() {
        stackMin.pop();
        return stack.pop();
    }
    
    public int getMin(){
        if(!stackMin.isEmpty()) {
            return stackMin.peek();
        } else {
            throw new RuntimeException("stack is empty!");
        }
    }
    
    
    
    public static void main(String[] args) {
        GetMinStack getMinStack = new GetMinStack();
        getMinStack.push(3);
        getMinStack.push(4);
        getMinStack.push(5);
        getMinStack.push(1);
        getMinStack.push(2);
        getMinStack.push(1);
        
//        System.out.println(getMinStack.stackMin.size());
        
        System.out.println("min is " + getMinStack.getMin());
        System.out.println(getMinStack.pop());
        System.out.println("min is " + getMinStack.getMin());
        System.out.println(getMinStack.pop());
        System.out.println("min is " + getMinStack.getMin());
        System.out.println(getMinStack.pop());
        System.out.println("min is " + getMinStack.getMin());
        System.out.println(getMinStack.pop());
        System.out.println("min is " + getMinStack.getMin());
        System.out.println(getMinStack.pop());
        System.out.println("min is " + getMinStack.getMin());
        System.out.println(getMinStack.pop());

    }
}
