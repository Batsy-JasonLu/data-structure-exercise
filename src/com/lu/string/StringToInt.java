package com.lu.string;

/**
 * 
 * @author 给定一个字符串转为整数，要求属于32位整数范围，不能0开头，不能有其他字符。
 *
 */
public class StringToInt {

    // 该方法与原书不同，这里提前处理了第一位数，然后关于溢出的判断一定要在最后一位的前一位，即不能等加上数溢出之后再去判断，那种判断在java中没有作用，所以在加上最后一位之前，先判断之前的数是否等于integer.max/10，然后看最后一个数是否大于7即可。
    public int Str2Int(String str) {
        if (str == null) {
            return 0;
        }
        
        char[] c = str.toCharArray();
        int cur = 0;
        int num = 0;
        boolean isFirstMinus = false;
        
        if (c[0] - '0' == 0) {
            return 0;
        }
        
        int i;
        if (c[0] == '-') {
            isFirstMinus = true;
            i = 1;
        } else {
            i = 0;
        }
        
        for (; i < c.length; i++) {
            cur = c[i] - '0';
            
            if (cur <= 9 && cur >= 1) {
                if (i == c.length - 1) {
                    if (num == Integer.MAX_VALUE / 10 && cur > 7) {
                        return 0;
                    } else {
                        num = num * 10 + cur;
                    }
                } else {
                    num = num * 10 + cur;
                }
            } else {
                return 0;
            }
        }
        
        if (isFirstMinus) {
            num = Integer.valueOf(("-" + String.valueOf(num)));
        }
        
        return num;
    }
    
    public static void main(String[] args) {
        String s1 = "123";
        String s2 = "023";
        String s3 = "a13";
        String s4 = "-123";
        String s5 = "--123";
        String s6 = "1-123";
        String s7 = "2147483647";
        String s8 = "2147483648";
        StringToInt main = new StringToInt();
        System.out.println(main.Str2Int(s1));
        System.out.println(main.Str2Int(s2));
        System.out.println(main.Str2Int(s3));
        System.out.println(main.Str2Int(s4));
        System.out.println(main.Str2Int(s5));
        System.out.println(main.Str2Int(s6));
        System.out.println(main.Str2Int(s7));
        System.out.println(main.Str2Int(s8));
    }

}
