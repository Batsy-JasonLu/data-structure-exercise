package com.lu.sort;

/**
 * 
 * @author lu
 * 
 * @description 选择排序
 *
 */
public class SelectSort {

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        
        for (int i = 0; i < arr.length; i++) {
            int k = i;
            
            for (int j = i + 1; j < arr.length; j++) {
                
                if (arr[j] < arr[k]) {
                    k = j;
                }
            }
            
            if (k != i) {
                int tmp = arr[k];
                arr[k] = arr[i];
                arr[i] = tmp;
            }
        }
        
        print(arr);
    }
    
    public static void main(String[] args) {
        int[] arr = {3,6,1,5,16,8,7,0,11};
        SelectSort main = new SelectSort();
        main.sort(arr);
    }
    
    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
