package com.hpmai.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandCacluator {
    public static void main(String[] args) {
        String suffixExpression = "3 4 + 5 * 6 -";

        ArrayList<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList: " + rpnList);

        int result = caclculate(rpnList);
        System.out.println("result: " + result);
    }

    //将一个波兰表达式，依次将数据和运算符放入到Arraylist中
    public static ArrayList<String> getListString(String suffixExpression)
    {
        //这里是可以存入多位数的，因为两位数及以上不用空格隔开
        String[] s = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<String>();
        for (String ele:s) {
            list.add(ele);
        }
        return list;
    }

    public static int caclculate(List<String> list)
    {
        //用后缀表达式，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历Ls
        for (String ele : list)
        {
            if (ele.matches("\\d+"))  //如果匹配的是多位数
            {
                //入栈
                stack.push(ele);
            }else
            {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (ele.equals("+")) {
                    res = num1 + num2;
                } else if (ele.equals("-")) {
                    res = num1 - num2;
                } else if (ele.equals("*")) {
                    res = num1 * num2;
                } else if (ele.equals("/")) {
                    res = num1 / num2;
                }else
                {
                    throw new RuntimeException("运算符错误");
                }
                //把res 入栈
                stack.push(""+res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
