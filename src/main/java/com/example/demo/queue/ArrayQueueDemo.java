package com.example.demo.queue;

import java.util.Scanner;

/**
 * 数组模拟环形队列
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        //测试
        //创建一个队列
        ArrrayQueue arrrayQueue = new ArrrayQueue( 3 );
        //接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner( System.in );
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println( "s(show):显示队列" );
            System.out.println( "e(exit):退出程序" );
            System.out.println( "a(add):添加数据到队列" );
            System.out.println( "g(get):从队列取出数据" );
            System.out.println( "h(head):查看队列头的数据" );
            // 接收一个字符
             key=scanner.next().charAt( 0 );
            switch (key){
                case 's' :
                    arrrayQueue.showQueue();
                    break;
                case 'a' :
                    System.out.println("输出一个数:");
                    int value=scanner.nextInt();
                    arrrayQueue.addQueue( value );
                    break;
                case 'g' :
                        try {
                        int result=arrrayQueue.getQueue();
                            System.out.printf("取出数据是%d\n",result);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    break;
                case 'h' :
                    try {
                    int result=arrrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n",result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e' :
                  scanner.close();
                  loop=false;
                    break;
            default:
                break;
            }
        }
        System.out.println("程序退出~！");

    }


}

//用数组模拟队列
class ArrrayQueue {
    /**
     * 表示数组最大容量
     */
    private int maxSize;
    /**
     * 队列头
     */
    private int front;
    /**
     * 队列尾
     */
    private int rear;
    /**
     * 该数据用于存放队列，模拟队列
     */
    private int[] arr;

    /**
     * 创建队列的构造器
     */
    public ArrrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        //指向队列头部，分析出front是指向队列头的前一个位置
        front = -1;
        //指向队列尾，指向队列尾的数据（即就是队列最后一个数据）
        rear = -1;
    }

    /**
     * 判断队列是否满
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加到数据队列
     */
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println( "队列满了，无法添加数据~" );
            return;
        }
        rear++;//让rear  后移
        arr[rear] = n;
    }

    /**
     * h获取队列的数据，出队列
     */
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //通过抛出异常
            throw new RuntimeException( "队列空，不能取数据" );
        }
        front++;    //front后移
        return arr[front];
    }

    /**
     * 显示队列所有数据
     */
    public void showQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            System.out.println( "队列为空，没有数据" );
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf( "arr[%d]=%d\n", i, arr[i] );
        }
    }

    /**
     * 显示队列的头数据，注意不是取数据
     */
    public int headQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            //通过抛出异常
            throw new RuntimeException( "队列为空，没有数据" );
        }
        return arr[front + 1];
    }
}