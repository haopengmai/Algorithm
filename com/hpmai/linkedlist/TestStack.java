package com.hpmai.linkedlist;

import java.util.Stack;

public class TestStack {
    public static void main(String[] args) {
        //入栈
        Stack<String> stack = new Stack();
        stack.add("foo");
        stack.add("bar");
        stack.add("hpmai");

        //出栈
        while (!stack.isEmpty())
        {
            System.out.println(stack.pop());
        }
    }
}
