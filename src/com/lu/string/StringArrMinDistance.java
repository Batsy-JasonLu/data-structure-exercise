package com.lu.string;

/**
 * 
 * @author lu
 * 
 * @description 求数组中两个字符串间的最小距离，如1,3,3,2,3,1，字符为1,2，最小距离返回2。进阶问题：把查询的时间复杂度降为o(1)。
 *
 */
public class StringArrMinDistance {

    // 最接近原书解法的一次，用index1和index2去记录最近一次str1和str2的位置，然后用i-index1/index2，然后每次再与最小距离对比得出最小距离。
    public int getMinDistance(String[] strArr, String str1, String str2) {
        if (strArr == null || str1 == null || str2 == null) {
            return -1;
        }
        
        int index1 = -1;
        int index2 = -1;
        int count = Integer.MAX_VALUE;
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(str1)) {
                index1 = index1 == -1 ? i : index1;
                if (index2 != -1) {
                    count = Math.min(count, Math.abs(i - index2));
                    index1 = i;
                }
            }
            
            if (strArr[i].equals(str2)) {
                index2 = index2 == -1 ? i :index2;
                if (index1 != -1) {
                    count = Math.min(count, Math.abs(i - index1));
                    index2 = i;
                }
            }
        }
        
        if (count == Integer.MAX_VALUE) {
            return -1;
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        String[] strArr = {"1","3","3","3","2","3","3","1","3","2"};
        String str1 = "1";
        String str2 = "2";
        StringArrMinDistance main = new StringArrMinDistance();
        int count = main.getMinDistance(strArr, str1, str2);
        System.out.println(count);
    }

}
