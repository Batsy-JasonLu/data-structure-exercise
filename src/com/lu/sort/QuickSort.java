package com.lu.sort;

import java.util.Stack;

/**
 * 
 * @author lu
 * 
 * @description 快速排序
 *
 */
public class QuickSort {

    public void sort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = partition(arr, low, high);
            
            sort(arr, low, mid - 1);
            
            sort(arr, mid + 1, high);
        }
    }
    
    public int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        
        while (low < high) {
            
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high];
            
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        
        arr[low] = pivot;
        
        return low;
    }
    
    public void sortNoRecursion(int[] arr, int low, int high) {
        Stack<Integer> stack = new Stack<Integer>();
        
        if (low < high) {
            stack.push(high);
            stack.push(low);
            
            while (!stack.isEmpty()) {
                int l = stack.pop();
                int r = stack.pop();
                int mid = partition(arr, l, r);
                
                if (l < mid - 1) {
                    stack.push(mid - 1);
                    stack.push(l);
                }
                
                if (r > mid + 1) {
                    stack.push(r);
                    stack.push(mid + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3,6,1,5,16,8,7,0,11};
        QuickSort main = new QuickSort();
        main.sortNoRecursion(arr, 0, arr.length - 1);
        main.print(arr);
    }
    
    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    
}
