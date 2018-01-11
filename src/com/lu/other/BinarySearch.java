package com.lu.other;

/**
 * 
 * @author lu
 * 
 * @description 二分查找
 *
 */
public class BinarySearch {

    public int search(int[] arr, int target) {
        int start = 0;
        int mid = 0;
        int end = arr.length - 1;
        
        while (start <= end) {
            mid = start + (end - start) / 2;
            
            if (arr[mid] == target) {
                return mid;
            }
            
            if (arr[mid] < target) {
                start = mid + 1;
            }
            
            if (arr[mid] > target) {
                end = mid - 1;
            }
        }
        
        return start;
    }
    
    public int searchRecursion(int[] arr, int target, int start, int end) {
        if (start > end) {
            return start;
        }
        
        int mid =  start + (end - start) / 2;
        
        if (arr[mid] > target) {
            return searchRecursion(arr, target, start, mid - 1);
        } else if (arr[mid] < target) {
            return searchRecursion(arr, target, mid + 1, end);
        } else {
            return mid;
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {0, 1, 3, 5, 6, 7, 8, 11, 16};
        int target = 8;
        BinarySearch main = new BinarySearch();
        int res = main.search(arr, target);
        int res2 = main.searchRecursion(arr, target, 0, arr.length - 1);
        System.out.println(res);
        System.out.println(res2);
    }

}
