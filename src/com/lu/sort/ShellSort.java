package com.lu.sort;

/**
 * 
 * @author lu
 * 
 * @description 希尔排序
 *
 */
public class ShellSort {

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        
        for (int d = arr.length / 2; d > 0; d = d / 2) {
            
            // 下面这个部分和插入排序一致，只是1变成了d。
            for (int i = d; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - d;
                
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + d] = arr[j];
                    j -= d;
                }
                
                arr[j + d] = tmp;
            }
        }
        
        print(arr);
    }
    
    public static void main(String[] args) {
        int[] arr = {3,6,1,5,16,8,7,0,11};
        ShellSort main = new ShellSort();
        main.sort(arr);
    }
    
    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
