package com.lu.stack;

/**
 * 
 * @author lu
 * 
 * @description 基于数组实现顺序栈。
 *
 */
public class MyStack<E> {

    private Object[] data = null;
    private int top = -1; //栈容量
    private int maxSize = 0; //栈顶指针
    
    public MyStack() {
        this(10);
    }
    
    public MyStack(int size) {
        if (size > 0) {
            this.maxSize = size;
            data = new Object[size];
            top = -1;
        } else {
            throw new RuntimeException("初始化大小必须大于0！");
        }
    }
    
    public boolean empty() {
        return top == -1 ? true : false;
    }
    
    public boolean push(E e) {
        if (top == maxSize - 1) {
            throw new RuntimeException("栈已满！");
        } else {
            data[++top] = e;
            return true;
        }
    }
    
    public E peek() {
        if (top == -1) {
            throw new RuntimeException("栈已空");
        } else {
            return (E) data[top];
        }
    }
    
    public E pop() {
        if (top == -1) {
            throw new RuntimeException("栈已空");
        } else {
            return (E) data[top--];
        }
    }
    
    // 返回元素在栈中的位置，以1为起始。
    public int search(E e) {
        // 用i暂存top的位置。
        int i = top;
        
        while (top != -1) {
            if (peek() != e) {
                top--;
            } else {
                break;
            }
        }
        
        int result = top + 1;
        top = i;
        
        return result;
    }
    
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<Integer>();
        stack.push(3);
        stack.push(6);
        stack.push(7);
        stack.push(1);
        stack.push(9);
        
        System.out.println(stack.peek());
        
        System.out.println(stack.search(7));
        
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
