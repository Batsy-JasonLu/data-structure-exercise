package com.lu.string;

/**
 * 
 * @author lu
 * 
 * @description 去掉字符串中连续k个0，如a0000b000，k为3，返回a0000b。
 *
 */
public class DeleteStringKZero {

    // 该方法取每一个字符值判断，如果为0，则记下起始位置，并开始计算0的个数，当遇到的字符不为0时，判断0的个数，如果等于k，从开始位置到结束，赋值0，这样转string时，字符会被去掉，之后把位置和计数重新赋为0。
    public String deleteKZero(String str, int k) {
        if (str == null || k < 1) {
            return str;
        }
        
        char[] c = str.toCharArray();
        int cur = 0;
        int count = 0;
        int index = -1;
        
        for (int i = 0; i < c.length; i++) {
            cur = c[i] - '0';
            
            // index == -1 ? i : index，这个操作可以保持index一直保存起始位置。
            if (cur == 0) {
                count ++;
                index = index == -1 ? i : index;
            } else {
                if (count == k) {
                    while (count != 0) {
                        c[index] = 0;
                        index ++;
                        count --;
                    }
                }
                index = -1;
                count = 0;
            }
        }
        
        if (count == k) {
            while (count != 0) {
                c[index] = 0;
                index ++;
                count --;
            }
        }
        
        return String.valueOf(c);
    }
    
    public static void main(String[] args) {
        String str = "a0000b000c000d0000";
        DeleteStringKZero main = new DeleteStringKZero();
        String res = main.deleteKZero(str, 3);
        System.out.println(res);
    }
    
}
