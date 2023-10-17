package com.hpmai.linkedlist;

import java.util.Stack;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {

    }


    static class DoubleLinkedList {
        //先定义一个头节点，头节点不要变
        private HeroNode head = new HeroNode(0, "", "");

        public HeroNode getHead()
        {
            return  head;
        }

        //添加节点到单向链表,尾插法(找到当前链表的最后一个节点，把最后这个节点的next域指向新插入的节点)
        public void add(HeroNode heroNode) {
            //临时的节点
            HeroNode temp = head;
            //遍历找到最后
            while (true) {
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            //当while循环结束的时候,next就指向了最后一个
            temp.next = heroNode;
            heroNode.pre =  temp;
        }



        //不能添加重复的No
        public void addByOrder(HeroNode heroNode) {
            //临时的节点
            HeroNode temp = head;
            boolean flag = false; //flag用来标志添加的节点是否已经存在
            //遍历找到最后
            while (true) {
                if (temp.next == null) {
                    break;
                }  //temp找到最后了
                if (temp.next.no == heroNode.no) {
                    flag = true;
                    break;
                }
                if (temp.next.no > heroNode.no) {
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                System.out.println("节点已存在");
            } else {
                heroNode.next = temp.next;
                temp.next = heroNode;
            }
        }

        //根据newHeroNode节点的 no 来进行修改
        public void update(HeroNode newHeroNode) {
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            //临时的节点
            HeroNode temp = head.next;
            boolean flag = false; //flag用来标志添加的节点是否已经存在
            while (true) {
                if (temp == null) //前面已经判断过空链表的情况了，此处为判断是否到了链表的最后端
                {
                    break;
                }
                if (temp.no == newHeroNode.no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                temp.name = newHeroNode.name;
                temp.nickName = newHeroNode.nickName;
            }
        }

        //删除节点
        //双向链表,可以直接找到待删除的节点
//        找到后,temp 就是要删除的那个节点
        public void delete(int no) {
            HeroNode temp = head.next;
            boolean found = false;//标志是否找到待删除的节点
            if (temp == null) {
                System.out.println("链表为空");
                return;
            }
            while (true) {
                if (temp == null) //前面已经判断过空链表的情况了，此处为判断是否到了最后一个节点的next域
                {
                    break;
                }
                if (temp.no == no) {
                    found = true;
                    break;
                }
                temp = temp.next;
            }
            if (found)
            {
                temp.pre.next = temp.next;
                //如果是双向链表的最后一个节点的话，就不需要执行以下这句话，否则会出现空指针异常
                if (temp.next!=null)
                {
                    temp.next.pre = temp.pre;
                }
            }
        }

        //显示链表
        public void list() {
            if (head.next == null) {
                System.out.println("List is null");
                return;
            }
            HeroNode temp = head.next;//不用遍历头节点，头节点没有数据
            while (true) {
                if (temp == null) {
                    break;
                }
                System.out.println(temp);
                temp = temp.next;
            }
        }

        //逆序打印
        public void reversePrint(HeroNode head)
        {
             if (head.next == null) {
                System.out.println("List is null");
                return;
            }
            Stack<HeroNode> nodeStack = new Stack<>();
            HeroNode temp = head.next;
             while (temp!= null)
             {
                 nodeStack.push(temp);
                 temp = temp.next;
             }
             while (!nodeStack.isEmpty())
             {
                 System.out.println(nodeStack.pop());
             }
        }
    }

    static class HeroNode {
        public int no;
        public String name;
        public String nickName;
        public HeroNode next; //指向下一个节点,默认为null
        public HeroNode pre;//指向前一个节点,默认为null

        public HeroNode(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }
}
