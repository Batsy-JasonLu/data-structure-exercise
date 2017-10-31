package com.lu.stack;

import java.util.Stack;

/**
 * 
 * @author lu
 * 
 * @description 用一个栈实现另一个栈的排序。
 *
 */
public class StackSortOtherStack {

    private Stack<Integer> helpStack;
    
    public StackSortOtherStack() {
        helpStack = new Stack<Integer>();
    }
    
    public void sortByRecursive(Stack<Integer> stack) {
        int cur = stack.pop();
        
        helpStackResort(cur);
        
        if(!stack.isEmpty()) {
            sortByRecursive(stack);
        } else {
            return;
        }
    }
    
    public void helpStackResort(int num) {
        if(helpStack.isEmpty()) {
            helpStack.push(num);
            return;
        }
        
        int cur = helpStack.pop();
        if(num < cur) {
            helpStackResort(num);
            helpStack.push(cur);
        } else {
            helpStack.push(num);
            return;
        }
    }
    
    
    public void sortByIterate(Stack<Integer> stack) {
        while(!stack.isEmpty()) {
            int cur = stack.pop();
            if(helpStack.isEmpty()) {
                helpStack.push(cur);
            } else {
                // 一定注意stack为空时，peek和poll异常的边界条件。
                while(!helpStack.isEmpty() && cur < helpStack.peek()) {
                    stack.push(helpStack.pop());
                }
                helpStack.push(cur);
            }
        }
    }
    
    
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.add(3);
        stack.add(1);
        stack.add(3);
        stack.add(4);
        stack.add(9);
        stack.add(6);
        stack.add(2);
        stack.add(7);
        
        StackSortOtherStack sortOtherStack = new StackSortOtherStack();
        sortOtherStack.sortByIterate(stack);
        
        System.out.println(sortOtherStack.helpStack.pop());
        System.out.println(sortOtherStack.helpStack.pop());
        System.out.println(sortOtherStack.helpStack.pop());
        System.out.println(sortOtherStack.helpStack.pop());
        System.out.println(sortOtherStack.helpStack.pop());
        System.out.println(sortOtherStack.helpStack.pop());
        System.out.println(sortOtherStack.helpStack.pop());
    }

}
