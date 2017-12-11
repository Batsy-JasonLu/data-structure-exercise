package com.lu.btree;

import java.util.HashMap;

/**
 * 
 * @author lu
 * 
 * @description 利用前序和中序数组生成后序数组，要求直接生成数组，不能重建二叉树。
 *
 */
public class GeneratePosArrByPreAndInArr {

    public int[] generatePosArr(int[] arrPre, int[] arrIn) {
        if (arrPre == null || arrIn == null) {
            return null;
        }
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < arrIn.length; i++) {
            map.put(arrIn[i], i);
        }
        
        int len = arrPre.length;
        int[] pos = new int[len];
        setPosArr(arrPre, 0, len - 1, arrIn, 0, len - 1, pos, len - 1, map);
        
        return pos;
    }
 
    public int setPosArr(int[] arrPre, int preStart, int preStop, int[] arrIn, int inStart, int inStop, int[] arrPos, int posStart, HashMap<Integer, Integer> map) {
        if (preStart > preStop) {
            return posStart;
        }
        
        arrPos[posStart--] = arrPre[preStart];
        int i = map.get(arrPre[preStart]);
        posStart = setPosArr(arrPre, preStop - inStop + i + 1, preStop, arrIn, i + 1, inStop, arrPos, posStart, map);
        
        return setPosArr(arrPre, preStart + 1, preStart + i - inStart, arrIn, inStart, i - 1, arrPos, posStart, map);
    }
    
    /*
            1
           / \
          2   3
         / \ / \ 
        4  5 6  7
          / \
         8   9
    */
    public static void main(String[] args) {
        int[] arrPre = {1,2,4,5,8,9,3,6,7};
        int[] arrIn = {4,2,8,5,9,1,6,3,7};
        
        GeneratePosArrByPreAndInArr main = new GeneratePosArrByPreAndInArr();
        int[] res = main.generatePosArr(arrPre, arrIn);
        main.printArray(res);
    }
    
    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    
}
