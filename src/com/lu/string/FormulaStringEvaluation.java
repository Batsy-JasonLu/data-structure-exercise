package com.lu.string;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * @author lu
 * 
 * @description 公式字符串求值，即给出一个如str="3+1*4"的字符串，返回计算结果，假设公式都是合法的且结果不会溢出。
 *
 */
public class FormulaStringEvaluation {

    // 将字符串转成list。
    public ArrayList<String> stringToList(String str) {
        ArrayList<String> result = new ArrayList<String>();
        String num = "";
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                num = num + str.charAt(i);
            } else {
                if (num != "") {
                    result.add(num);
                }
                result.add(str.charAt(i) + "");
                num = "";
            }
        }
        if (num != "") {
            result.add(num);
        }
        return result;
    }

    // 将中缀表达式转化为后缀表达式
    public ArrayList<String> InOrderToPostOrder(ArrayList<String> inOrderList) {

        ArrayList<String> result = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < inOrderList.size(); i++) {
            if (Character.isDigit(inOrderList.get(i).charAt(0))) {
                result.add(inOrderList.get(i));
            } else {
                switch (inOrderList.get(i).charAt(0)) {
                case '(':
                    stack.push(inOrderList.get(i));
                    break;
                case ')':
                    while (!stack.peek().equals("(")) {
                        result.add(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    while (!stack.isEmpty()
                            && compare(stack.peek(), inOrderList.get(i))) {
                        result.add(stack.pop());
                    }
                    stack.push(inOrderList.get(i));
                    break;
                }
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    // 计算后缀表达式
    public Integer calculate(ArrayList<String> postOrder) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < postOrder.size(); i++) {
            if (Character.isDigit(postOrder.get(i).charAt(0))) {
                stack.push(Integer.parseInt(postOrder.get(i)));
            } else {
                Integer back = (Integer) stack.pop();
                Integer front = (Integer) stack.pop();
                Integer res = 0;
                switch (postOrder.get(i).charAt(0)) {
                case '+':
                    res = front + back;
                    break;
                case '-':
                    res = front - back;
                    break;
                case '*':
                    res = front * back;
                    break;
                case '/':
                    res = front / back;
                    break;
                }
                stack.push(res);
            }
        }
        return (Integer) stack.pop();
    }

    // 比较运算符等级
    public static boolean compare(String peek, String cur) {
        if ("*".equals(peek)
                && ("/".equals(cur) || "*".equals(cur) || "+".equals(cur) || "-"
                        .equals(cur))) {
            return true;
        } else if ("/".equals(peek)
                && ("/".equals(cur) || "*".equals(cur) || "+".equals(cur) || "-"
                        .equals(cur))) {
            return true;
        } else if ("+".equals(peek) && ("+".equals(cur) || "-".equals(cur))) {
            return true;
        } else if ("-".equals(peek) && ("+".equals(cur) || "-".equals(cur))) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        FormulaStringEvaluation main = new FormulaStringEvaluation();
        String str = "48*((70-65)-43)+8*1";
        ArrayList<String> result = main.stringToList(str); // String转换为List
        result = main.InOrderToPostOrder(result); // 中缀变后缀
        int res = main.calculate(result); // 计算
        System.out.println(res);
    }

}
