package com.hpmai.queue;

public class ArrayQueue {
}

class Queue {
    private int maxSize;//最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//模拟队列

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;//队列头的前一个位置
        rear = -1;//指向最后一个数据
    }

    //判断队列是否满
    public boolean isFull()
    {
        return rear == maxSize - 1;
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
        rear++;
        arr[rear] =n;
    }

    //出列
    public int remove(int n)
    {
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        front++;
        return arr[front];
    }

    //显示队列
    public void showQueue()
    {
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    //显示队列头数据
    public int headQueue()
    {
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        return arr[front+1];
    }
}