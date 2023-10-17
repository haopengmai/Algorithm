package com.hpmai.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "30+2*6-2";
        //一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 定义相关的变量
        int index = 0;//用来扫描表达式的
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int result = 0;
        char ch = ' ';//将每次扫描得到的char放入到ch
        String keepNumber = "";//用于拼接多位数的
        //对输入的字符串进行扫描
        while (true)
        {
            ch = expression.substring(index, index +1).charAt(0);
            if(operStack.isOperation(ch))//   先判断是否为运算符
            {
                //判断当前的符号栈是否为空
                if(!operStack.isEmpty())
                {
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek()))//看运算的优先级
                    {
                        num2 = numStack.pop();
                        num1 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.cal(num1, num2, oper);
                        numStack.push(result);
                        operStack.push(ch);
                    }
                    else
                    {  //如果当前的操作符优先级大于符号栈中的peek优先级，则ch入栈
                        operStack.push(ch);
                    }
                }
                else
                {
                    //如果为空，则直接入栈
                    operStack.push(ch);
                }
            }
            else //这里如果是数，则直接入数栈是错的，比如70，就不能直接入栈啦！
            {
                keepNumber += ch;
                //看一下后面一位是否是数字
                //此处的index很有可能会出现数组越界的情况
                if (index == expression.length()-1)
                {
                    numStack.push(Integer.parseInt(keepNumber));
                }
                else
                {
                    if (operStack.isOperation(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNumber));
                        keepNumber = ""; //非常非常重要！！！！！！！1！！！！！！！！！！！
                        // keepNumber如果成功拼接上了，一定要重新清空
                    }
                }
            }
            index++;
            if (index >= expression.length()){break;}
        }

        //表达式扫描结束
        while(true)
        {
            //如果符号栈为空，则运算完成，此时，数栈中只有一个数字，该数字就是为result
            if(operStack.isEmpty()){break;}
            num2 = numStack.pop();
            num1 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1, num2, oper);
            numStack.push(result);
        }
        System.out.println("result = " + numStack.peek());
    }


    static class ArrayStack2 {
        private int maxSize;
        private int[] stack;
        private int top = -1;

        //初始构造
        public ArrayStack2(int maxSize) {
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
        public void push(int value) {
            //入栈之前先判断是否栈满
            if (isFull()) {
                System.out.println("栈满");
                return;
            }
            top++;
            stack[top] = value;
            return;
        }

        //出栈 -pop
        public int pop() {
            //出栈之前先判断是否为空栈
            if (isEmpty()) {
                throw new RuntimeException("Empty");
            }
            return stack[top--];
        }

        //返回栈顶
        public int peek()
        {
            return stack[top];
        }

        //遍历栈
        public void list() {
            if (isEmpty()) {
                System.out.println("栈空，没有数据");
                return;
            }
            //从栈顶开始输出数据
            for (int i = top; i >= 0; i--) {
                System.out.printf("stack[%d]=%d\n", i, stack[i]);
            }
        }

        //返回运算符的优先级
        public int priority(int operation) {
            if (operation == '*' || operation == '/') {
                return 1;
            } else if (operation == '+' || operation == '-') {
                return 0;
            } else {
                return -1;
            }
        }

        //判断是否是运算符
        public boolean isOperation(char operation) {
            return operation == '+' || operation == '-' || operation == '*' || operation == '/';
        }

        //计算方法
        public int cal(int num1, int num2, int operation)
        {
            int result = 0;
            switch (operation)
            {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num2 - num1;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num2 / num1;
                    break;
                default:
                    break;
            }
            return result;
        }
    }

}
