package com.lu.sort;

/**
 * 
 * @author lu
 * 
 * @description 冒泡排序
 *
 */
public class BubbleSort {

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                int tmp = 0;
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        
        print(arr);
    }
    
    public static void main(String[] args) {
        int[] arr = {3,6,1,5,16,8,7,0,11};
        BubbleSort main = new BubbleSort();
        main.sort(arr);
    }
    
    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
