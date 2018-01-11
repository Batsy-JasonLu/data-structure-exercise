package com.lu.sort;

/**
 * 
 * @author lu
 * 
 * @description 堆排序
 *
 */
public class HeapSort {

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        
        buildMaxHeap(arr);
        
        for (int i = arr.length - 1; i >= 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            adjustHeapNoRecursion(arr, i, 0);
        }
        
        print(arr);
    }
    
    public void buildMaxHeap(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            adjustHeapNoRecursion(arr, arr.length, i);
        }
    }
    
    // 调整为最大堆，递归方法。
    public void adjustHeap(int[] arr, int size, int parent) {
        int left = parent * 2 + 1;
        int right = parent * 2 + 2;
        int largest = parent;
        
        if (left < size && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < size && arr[right] > arr[largest]) {
            largest = right;
        }
        
        if (parent != largest) {
            int tmp = arr[parent];
            arr[parent] = arr[largest];
            arr[largest] = tmp;
            adjustHeap(arr, size, largest);
        }
    }
    
    public void adjustHeapNoRecursion(int[] arr, int len, int index) {
        int tmp = arr[index];
        int child = 2 * index + 1;
        
        while (child < len) {
            if (child + 1 < len && arr[child] < arr[child + 1]) {
                ++child;
            }
            
            if (arr[child] > arr[index]) {
                arr[index] = arr[child];
                index = child;
                child = 2 * index + 1;
            } else {
                break;
            }
            
            arr[index] = tmp;
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {3,6,1,5,16,8,7,0,11};
        HeapSort main = new HeapSort();
        main.sort(arr);
    }
    
    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
