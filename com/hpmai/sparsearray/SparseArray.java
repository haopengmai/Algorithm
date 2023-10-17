package com.hpmai.sparsearray;

import org.junit.jupiter.api.Test;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组
        int[][] chessAra = new int[11][11];
//        1表示黑子,2表示蓝子
        chessAra[1][2]= 1;
        chessAra[2][3]= 2;

        for (int[] row : chessAra)
        {
            for (int data : row)
            {
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        //二维数组转稀疏数组的思想
//        1.遍历二维数组，将非0的值count一下，方便后面存放到新的稀疏数组中
        int sum = 0;
        for (int i = 0; i < 11; i++)
        {
            for (int j = 0; j < 11; j++)
            {
                if (chessAra[i][j]!=0)
                {
                    sum++;
                }
            }
        }

        //创建稀疏数组,只有知道了sum，才知道要创建多大的SparseAra
        int[][] SparseAra = new int[sum + 1][3];
        //第一行赋值
        SparseAra[0][0] = 11;
        SparseAra[0][1] = 11;
        SparseAra[0][2] = sum;


        int count = 0;//用于记录是第几个非0数据
        for (int i = 0; i <11 ; i++)
        {
            for (int j = 0; j <11; j++)
            {
                if (chessAra[i][j]!=0)
                {
                    count++;
                    SparseAra[count][0] =i;
                    SparseAra[count][1] =j;
                    SparseAra[count][2] = chessAra[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("稀疏数组为：");
        for (int i = 0; i < SparseAra.length; i++) {
            System.out.printf("%d\t%d\t%d\n", SparseAra[i][0], SparseAra[i][1], SparseAra[i][2]);
        }


        //将稀疏数组恢复原始的二维数组
        int[][] chessAra2 = new int[SparseAra[0][0]][SparseAra[0][1]];
        for (int i = 1; i < SparseAra.length; i++) {
            chessAra2[SparseAra[i][0]][SparseAra[i][1]]=SparseAra[i][2];
        }



        System.out.println("输出恢复后的二维数组");
        for (int row[]:chessAra2)
        {
            for (int data:row)
            {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
