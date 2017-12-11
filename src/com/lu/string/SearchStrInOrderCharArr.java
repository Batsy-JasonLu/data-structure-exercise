package com.lu.string;

/**
 * 
 * @author lu
 * 
 * @description 给定一个的字符串数组，有些位置有null，不为null的位置上，字符串有序出现，再给一个字符串，返回该字符串在数组中的最左位置。
 *
 */
public class SearchStrInOrderCharArr {

    // 与原书方法不同，原书中使用了二分查找。一旦看到有些，就要考虑二分查找，性能上比直接遍历要好。
    public int getIndex(String[] strs, String str) {
        if (strs == null || str == null) {
            return -1;
        }
        
        for (int i = 0; i < strs.length; i++) {
            if (String.valueOf(strs[i]).equals(str)) {
                return i;
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        String[] strs = {null, "a", null, "a", null, "b"};
        String str = "a";
        SearchStrInOrderCharArr main = new SearchStrInOrderCharArr();
        int res = main.getIndex(strs, str);
        System.out.println(res);
    }

}
