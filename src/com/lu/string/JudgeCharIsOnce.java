package com.lu.string;

/**
 * 
 * @author lu
 * 
 * @description 判断字符数组中的每个字符是否都只出现过一次。进阶：要求空间复杂度为o(1)，时间复杂度尽可能小。
 *
 */
public class JudgeCharIsOnce {

    // 原问题，用map数组加减的方式即可实现。
    // 进阶问题，实现方法是把数组排序后再判断，而排序是其中的重点，要空间复杂度为o(1)的排序，还有时间复杂度尽量小，则选取堆排序。
    // 要注意！递归的堆排序的空间复杂度为o(logn)，只有非递归的才为o(1)。
    public boolean isOnce(char[] c) {
        if (c == null) {
            return false;
        }
        
        int[] map = new int[256];
        int cur = 0;
        
        for (int i = 0; i < c.length; i++) {
            cur = c[i] - '0';
            map[cur] ++;
        }
        
        for (int i = 0; i < map.length; i++) {
            if (map[i] > 1) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        char[] c = {'a','b','c'};
        JudgeCharIsOnce main = new JudgeCharIsOnce();
        boolean res = main.isOnce(c);
        System.out.println(res);
    }

}
