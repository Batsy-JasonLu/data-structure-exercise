package com.lu.stack;

import java.util.Stack;

/**
 * 
 * @author lu
 * 
 * @description 用两个栈实现队列，具备基本的add/poll/peek操作
 *
 */
public class TwoStacksToQueue {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    
    public TwoStacksToQueue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }
    
    public void add(int num) {
        stack1.add(num);
    }
    
    public int poll() {
        if(stack1.isEmpty() && stack2.isEmpty()) {
            throw new RuntimeException("queue is empty!");
        } else {
            if(stack2.isEmpty()) {
                while(!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
        }
        
        return stack2.pop();
    }
    
    // 这里的poll和peek，其中只要其中一个被调用了，就会把stack1中的数push到stack2中，
    // 因为stack都是全局变量，所以后面一个操作就不再复制，直接poll或peek即可。
    
    public int peek() {
        if(stack1.isEmpty() && stack2.isEmpty()) {
            throw new RuntimeException("queue is empty!");
        } else {
            if(stack2.isEmpty()) {
                while(!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
        }
        
        return stack2.peek();
    }    
    
    
    
    public static void main(String[] args) {

        TwoStacksToQueue queue = new TwoStacksToQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        
        System.out.println(queue.poll());
        System.out.println("peek is " + queue.peek());
        System.out.println(queue.poll());
        System.out.println("peek is " + queue.peek());
        System.out.println(queue.poll());
        System.out.println("peek is " + queue.peek());
        System.out.println(queue.poll());
        System.out.println("peek is " + queue.peek());
        System.out.println(queue.poll());
    }

}
