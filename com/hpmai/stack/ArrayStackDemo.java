package com.hpmai.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {

    }


    class ArrayStack {
        private int maxSize;
        private int[] stack;
        private int top = -1;

        //初始构造
        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[this.maxSize];
            top = -1;
        }

        //栈满
        public boolean isFull() {
            return top == maxSize - 1;
        }

        //栈空
        public boolean isEmpty() {
            return top == -1;
        }

        //入栈 -push
        public void push(int value)
        {
            //入栈之前先判断是否栈满
            if (isFull())
            {
                System.out.println("栈满");
                return;
            }
            top++;
            stack[top] = value;
            return;
        }

        //出栈 -pop
        public int pop()
        {
            //出栈之前先判断是否为空栈
            if (isEmpty())
            {
                throw new RuntimeException("Empty");
            }
            return stack[top--];
        }

        //遍历栈
        public void list()
        {
            if(isEmpty())
            {
                System.out.println("栈空，没有数据");
                return;
            }
            //从栈顶开始输出数据
            for (int i = top; i >= 0; i--)
            {
                System.out.printf("stack[%d]=%d\n",i,stack[i]);
            }
        }
    }
}
