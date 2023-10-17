package com.hpmai.linkedlist;

public class yuesefu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(10000000);
//        circleSingleLinkedList.showBoy();

        //测试小孩出圈
        circleSingleLinkedList.countBoy(1,2,10000000);
    }

    //创建一个环形的单向链表
    static class CircleSingleLinkedList {
        private Boy first = null;

        public void addBoy(int nums)
        {
            if (nums < 1)
            {
                System.out.println("出错了！");
            }
            Boy curBoy = null;//辅助指针
            for (int i = 1; i <= nums ; i++)
            {
                //根据编号创建节点
                Boy boy = new Boy(i);
                //如果是第一个小孩的话,就让first指向boy
                if (i == 1)
                {
                    first =boy;
                    first.setNext(first);//构成环装
                    curBoy = first;
                }
                else
                {
                    curBoy.setNext(boy);
                    boy.setNext(first);
                    curBoy = boy;
                }
            }
        }

        //遍历当前的环形链表
        public void showBoy()
        {
            if (first == null)
            {
                System.out.println("没有任何小孩");
                return;
            }
            Boy curBoy = first;
            while (true)
            {
                System.out.printf("小孩的编号%d \n",curBoy.getNo());
                if (curBoy.getNext() == first) {
                    break;
                }
                curBoy = curBoy.getNext();
            }
        }

        /**
         *
         * @param Startno 表示从第几个小孩开始数数
         * @param countNum 表示数多少下
         * @param nums 一共有多少个小孩
         */
        public void countBoy(int Startno,int countNum,int nums)
        {
            //先对数据进行校验
            if (first==null||nums<=0||Startno<1||countNum<=0||Startno>nums)
            {
                System.out.println("输入的参数有误");
                return;
            }
            //将helper这个指针指向最后
            Boy helper = first;
            while (true)
            {
                if (helper.getNext()==first)
                {
                    break;
                }
                helper = helper.getNext();
            }
            //从第几个位置开始，就先将helper和first移动到第几个位置
            for (int i = 0; i < Startno-1; i++)
            {
                first = first.getNext();
                helper = helper.getNext();
            }
            //
            while (true)
            {
                if(first == helper){break;}
                for (int i = 0; i < countNum; i++)
                {
                    first = first.getNext();
                    helper = helper.getNext();
                }
                //first指向的就是要出圈的小孩
                System.out.printf("小孩%d出圈了\n",first.getNo());
                first = first.getNext();
                helper.setNext(first);
            }
            //最后的小孩
            System.out.println("最后的小孩是："+helper.getNo());
        }
    }

    static class Boy
    {
        private int no;
        private Boy next;

        public Boy(int no)
        {
            this.no = no;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public Boy getNext() {
            return next;
        }

        public void setNext(Boy next) {
            this.next = next;
        }
    }

}
