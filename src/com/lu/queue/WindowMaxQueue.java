package com.lu.queue;

import java.util.LinkedList;

/**
 * 
 * @author lu
 * 
 * @description 有一个整型数组和一个大小为w的窗口，窗口从最左滑到最右，每次滑到一个位置，得到一个最大值。输入数组和w，输出一个最大值数组，要求复杂度为O(n)。
 *
 */
public class WindowMaxQueue {

    private LinkedList<Integer> queue;
    
    public WindowMaxQueue() {
        this.queue = new LinkedList<Integer>();
    }
    
    public int[] getWindowMaxQueue(int[] arr, int win) {
        int res[] = new int[arr.length - win + 1];
        int index = 0;
        // 队头永远是最大值，这里维持了一个最大队列。
        for (int i = 0; i < arr.length; i++) {
            if(queue.isEmpty()) {
                queue.addLast(i);
            } else {
                while(!queue.isEmpty() && arr[queue.peekLast()] < arr[i]) {
                    queue.pollLast();
                }
                queue.addLast(i);
            }
            
            // 当队头的下标等于i-win时，说明一直在入队列，且队列已满，所以要队头元素出队列。
            if(queue.peekFirst() == i-win) {
                queue.pollFirst();
            }
            
            // 每次把队头元素加入结果数组。
            if(i >= win - 1) {
                res[index] = arr[queue.peekFirst()];
                index ++;
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        WindowMaxQueue windowMaxQueue = new WindowMaxQueue();
        int[] res = windowMaxQueue.getWindowMaxQueue(arr, 3);
        
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
    
}
