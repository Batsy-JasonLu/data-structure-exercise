package com.lu.stack;

/**
 * 
 * @author lu
 *
 * @description 基于数组实现顺序队列。
 * 
 */
public class MyQueue<E> {

    private Object[] data = null;
    private int maxSize = 0;
    private int front = 0;
    private int rear = 0;
    
    public MyQueue() {
        this(10);
    }
    
    public MyQueue(int size) {
        if (size > 0) {
            this.maxSize = size;
            data = new Object[size];
            front = rear = 0;
        } else {
            throw new RuntimeException("初始化队列大小必须大于0！");
        }
    }
    
    public boolean empty() {
        return front == rear ? true : false;
    }
    
    public boolean add(E e) {
        if (rear == maxSize) {
            throw new RuntimeException("队列已满！");
        } else {
            data[rear++] = e;
            return true;
        }
    }
    
    public E poll() {
        if (empty()) {
            throw new RuntimeException("队列已空");
        } else {
            E value = (E) data[front];
            // 把该位置为null，再++到下一位置。
            data[front++] = null;
            return value;
        }
    }
    
    public E peek() {
        if (rear == front) {
            throw new RuntimeException("队列已空");
        } else {
            return (E) data[front];
        }
    }
    
    public int length() {
        return rear - front;
    }
    
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();
        queue.add(6);
        queue.add(1);
        queue.add(9);
        queue.add(7);
        queue.add(8);
        
        System.out.println(queue.length());
        
        System.out.println(queue.peek());
        
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}
