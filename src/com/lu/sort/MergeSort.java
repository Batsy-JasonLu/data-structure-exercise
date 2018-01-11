package com.lu.sort;

/**
 * 
 * @author lu
 * 
 * @description 归并排序
 *
 */
public class MergeSort {

    // 递归归并
    public void sort(int[] arr, int low, int high) {
        int mid = (low + high) / 2;
        
        if (low < high) {
            sort(arr, low, mid);
            
            sort(arr, mid + 1, high);
            
            merge(arr, low, mid, high);
        }
        
    }
    
    public void merge(int[] arr, int low, int mid, int high) {
        int[] tmp = new int[high - low + 1];
        
        int i = low;
        int j = mid + 1;
        int k = 0;
        
        while (i <= mid && j <= high) {
            if (arr[i] < arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        
        while (i <= mid) {
            tmp[k++] = arr[i++];
        }
        
        while (j <= high) {
            tmp[k++] = arr[j++];
        }
        
        for (int t = 0; t < tmp.length; t++) {
            arr[t + low] = tmp[t];
        }
    }
    
    // 非递归归并
    public void sortNoRecursion(int[] arr) {
        int len = 1;
        
        while (len <= arr.length) {
            for (int i = 0; i < arr.length; i += 2*len) {
                mergeNoRecursion(arr, i, len);
            }
            
            len *= 2;
        }
    }
    
    public void mergeNoRecursion(int[] arr, int i, int len) {
        int start = i;
        int len_i = i + len;
        int j = i + len;
        int len_j = j + len;
        int[] tmp = new int[2 * len];
        int k = 0;

        while (i < len_i && j < len_j && j < arr.length) {
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        
        // 这里i不能超过数组长度
        while (i < len_i && i < arr.length) {
            tmp[k++] = arr[i++];
        }

        while (j < len_j && j < arr.length) {
            tmp[k++] = arr[j++];
        }
        
        k = 0;
        
        while (start < j && start < arr.length) {
            arr[start++] = tmp[k++];
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {3,6,1,5,16,8,7,0,11};
        MergeSort main = new MergeSort();
        main.sort(arr, 0, arr.length - 1);
//        main.sortNoRecursion(arr);
        main.print(arr);
    }
    
    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    
}
