package com.hpmai.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//中缀表达式转后缀表达式
public class middle_transferto_Poland {
    public static void main(String[] args) {
        String middleExpression = "1+((2+3)*4)-5";
        //直接对str进行操作，不方便，先将中缀表达式拆分为对应的list
        List<String> resultlist = toInfixExpressionList(middleExpression);
        System.out.println(resultlist);
        List<String> infixTrans = InfixTrans(resultlist);
        System.out.println(infixTrans);

    }

    //将中缀表达式转换为对应的list
    public static List<String> toInfixExpressionList(String s) {
        ArrayList<String> arrayList = new ArrayList<>();
        int i=0; //cursor,用于遍历字符串
        String temp;//对多位数的拼接
        char c;

        while (i < s.length()){
            //不是操作符，考虑是否有多位数的情况
            if (!IsOper(String.valueOf(c = s.charAt(i)))) {
                temp = "";
                while (i < s.length() && !IsOper(String.valueOf(c = s.charAt(i)))) {
                    temp += String.valueOf(c);
                    i++;
                }
                arrayList.add(temp);
            }
            //如果是操作符，则直接是单个位置
            else if (IsOper(String.valueOf(c = s.charAt(i)))) {
                arrayList.add(String.valueOf(c));
                i++;
            }
        }
        return arrayList;
    }

    public static boolean IsOper(String s)
    {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")||s.equals("(")||s.equals(")");
    }

    //中缀表达式转后缀表达式
    public static List<String> InfixTrans(List<String> ls) {
        //初始化栈,
        Stack<String> stack = new Stack<>();//符号栈
        ArrayList<String> middle_list = new ArrayList<>();//存储中间结果的list

        //遍历ls
        for (String ele : ls) {
            //如果是一个数的话，直接进list
            if (ele.matches("\\d+")) {
                middle_list.add(ele);
            } else if (ele.equals("(")) {
                stack.push(ele);
            } else if (ele.equals(")")) {
                while (!stack.peek().equals("(")) {
                    middle_list.add(stack.pop());
                }
                stack.pop(); // 消除"("
            } else {
                //当ele的优先级小于stack中栈顶的优先级,则先将stack中栈顶的元素放到list中去，ele再次进行比较
                while (stack.size() != 0 && Operation.getOperationprior(stack.peek()) >= Operation.getOperationprior(ele)) {
                    middle_list.add(stack.pop());
                }
                //还需要将ele压进stack中
                stack.push(ele);
            }
        }

        //将stack中剩余的运算符以此加入list中
        while (stack.size()!= 0)
        {
            middle_list.add(stack.pop());
        }

        return middle_list;
    }

}

class Operation
{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    private static int kuohao = 0;

    public static int getOperationprior(String s)
    {
        int result = 0;
        switch (s)
        {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            case "(":
                result = kuohao;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}
