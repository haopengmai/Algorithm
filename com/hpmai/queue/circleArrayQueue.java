package com.hpmai.queue;

public class circleArrayQueue {
    public static void main(String[] args) {

    }
}

class circleQueue {
    private int maxSize;//最大容量
    private int front;//队列头,circlequeue指向第一个数据
    private int rear;//队列尾,circlequeue指向最后一个数据的下一个位置
    private int[] arr;//模拟队列

    public circleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    //判断队列是否满
    public boolean isFull()
    {
        return (rear+1)%maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty()
    {
        return  front == rear;
    }

    //入列
    public void add(int n)
    {
        //判断是否满
        if (isFull()) {
            System.out.println("queue is full");
            return;
        }

        arr[rear] =n;
        rear = (rear+1)%maxSize;
    }

    //出列
    public int remove(int n)
    {
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        int tempvalue = arr[front];//不能直接返回！如果直接返回的话,front就没有移动的机会了!!!
        front = (front+1)%maxSize;
        return tempvalue;
    }

    //显示队列
    public void showQueue()
    {
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        //要从front开始遍历了!
        for (int i = front; i < front+getSize(); i++) {
            System.out.printf("arr[%d] = %d\n", i%maxSize, arr[i%maxSize]);
        }
    }

    //求出当前数组的有效数据个数
    public int getSize()
    {
        return (rear+maxSize-front)%maxSize;
    }

    //显示队列头数据
    public int headQueue()
    {
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        return arr[front];
    }
}