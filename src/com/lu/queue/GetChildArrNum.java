package com.lu.queue;

import java.util.LinkedList;

public class GetChildArrNum {

    public int getRequireChildArrNum(int[] arr, int num) {
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        int i = 0;
        int j = 0;
        int res = 0;
        
        while (i < arr.length) {
            while (j < arr.length) {
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
                    qmin.pollLast();
                }
                qmin.addLast(j);
                
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
                    qmax.pollLast();
                }
                qmax.addLast(j);
                
                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                }
                j++;
            }
            
            if (qmax.peekFirst() == i) {
                qmax.pollFirst();
            }
            
            if (qmin.peekFirst() == i) {
               qmin.pollFirst(); 
            }
            
            res = res + (j - i);
            i++;
        }
        return res;
    }
    
    
    public static void main(String[] args) {
        int arr[] = {4,1,2};
        int num = 2;
        GetChildArrNum getChildArrNum = new GetChildArrNum();
        int res = getChildArrNum.getRequireChildArrNum(arr, num);
        System.out.println(res);
    }

}
