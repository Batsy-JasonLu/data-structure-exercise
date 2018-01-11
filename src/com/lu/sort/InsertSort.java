package com.lu.sort;

/**
 * 
 * @author lu
 * 
 * @description 插入排序
 *
 */
public class InsertSort {

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;
            
            while (j >= 0 && tmp < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            
            arr[j + 1] = tmp;
        }
    
        print(arr);
    }
    
    public static void main(String[] args) {
        int[] arr = {3,6,1,5,16,8,7,0,11};
        InsertSort main = new InsertSort();
        main.sort(arr);
    }
    
    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
