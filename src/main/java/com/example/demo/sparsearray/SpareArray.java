package com.example.demo.sparsearray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 五子棋
 */
public class SpareArray {

    public static void main(String[] args) throws Exception {
        //创建一个原始的二维数组11*11
        //0：代表没有棋子，1代表黑子，2代表蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][4] = 1;
        chessArr1[2][4] = 2;
        chessArr1[2][2] = 1;
        //输出原始的二维数组
        System.out.println("原始的二维数组:");
        for (int[] row : chessArr1) {
            for (int data : row) {
              /*  System.out.printf( "%d\t", data );*/
                System.out.print(data+"\t");
            }
            System.out.println();
        }
        //将二维数组转为为稀疏数组
        //1）  先遍历二维数组
        int sum=0;
        System.out.println("chessArr1.length:"+chessArr1.length);
        System.out.println("chessArr1[].length:"+chessArr1[0].length);
        for (int i=0;i<chessArr1.length;i++){

            for (int j=0;j<chessArr1[i].length;j++){
                if(chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println("sum"+sum);

        //2.创建稀疏数组
            int sparseArr[][] =new int[sum+1][3];
            //给稀疏数组赋值
            sparseArr[0][0]=chessArr1.length;
            sparseArr[0][1]=chessArr1[0].length;
            sparseArr[0][2]=sum;
            //遍历二维数组将非0的值存放到sparseArr
            int count=0;
        for (int i=0;i<chessArr1.length;i++){

            for (int j=0;j<chessArr1[i].length;j++){
                if(chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
            }
            }
        }
        //输出稀疏数组的形式
        System.out.println("得到的稀疏数组：！！！！！");
        for (int i=0;i<sparseArr.length;i++){

            System.out.println(sparseArr[i][0]+"\t"+sparseArr[i][1]+"\t"+sparseArr[i][2]);
        }
        File file = new File("E:\\mayun\\dataStructures\\sparseArr.txt");  //存放数组数据的文件

        FileWriter out = new FileWriter(file);  //文件写入流

        //将数组中的数据写入到文件中。每行各数据之间TAB间隔
        for(int i=0;i<sparseArr.length;i++){
            for(int j=0;j<sparseArr[i].length;j++){
                out.write(sparseArr[i][j]+"\t");
            }
            out.write("\r\n");
        }
        out.close();

        //将稀疏数组恢复成二维数组
        //1)先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组

     /* int[][] chessArr2=new int[sparseArr[0][0]][sparseArr[0][1]];*/
        int[][] chessArr2=null;
        BufferedReader in = new BufferedReader(new FileReader(file));  //
        String line;  //一行数据
        int row=0;
        //逐行读取，并将每个数组放入到数组中
        while((line = in.readLine()) != null){
            String[] temp = line.split("\t");
             if(row==0){
                 chessArr2=new int[Integer.parseInt( temp[0] )][Integer.parseInt( temp[1] )];
             }else {
                 for (int j = 0; j < temp.length; j++) {
                     chessArr2[Integer.parseInt( temp[0] )][Integer.parseInt( temp[1] )] = Integer.parseInt( temp[2] );
                 }
             }
            row++;
        }
        in.close();

        //2)在读取稀疏数组的后j几行数据（从第二行开始），并付给原始的二维数组即可
      /*  for(int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }*/
        //得到的原始二维数组
        System.out.println("读取到的原始二维数组：~~~~");
        for (int[] row2 : chessArr2) {
            for (int data : row2) {
                /*  System.out.printf( "%d\t", data );*/
                System.out.print(data+"\t");
            }
            System.out.println();
        }
    }


}
