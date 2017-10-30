package com.lu.stack;

import java.util.Stack;

/**
 * 
 * @author lu
 *
 * @description 只用递归函数逆序一个栈
 *
 */
public class RecursiveReverseStack {

    // 将栈底元素移除并返回
    public int removeAndGetLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if(stack.isEmpty()) {
            return result;
        }
        
        int last = removeAndGetLastElement(stack);
        stack.push(result);
        return last;
    }
    
    public void reverse(Stack<Integer> stack) {
        if(stack.isEmpty()) {
            return;
        }
        int num = removeAndGetLastElement(stack);
        reverse(stack);
        stack.push(num);
    }
    
    
    public static void main(String[] args) {

    }

}
