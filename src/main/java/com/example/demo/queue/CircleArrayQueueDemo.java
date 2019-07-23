package com.example.demo.queue;

import java.util.Scanner;

/**
 * 环形队列数组
 */
public class CircleArrayQueueDemo {


    public static void main(String[] args) {
        //测试
        //创建一个数组环形队列
        System.out.println( "数组模拟环形队列:~~~~~~~~~~~" );
                     //这里设置为4，其实只有3个可用空间，最后一个预留
        CircleArray circleArray = new CircleArray( 4);
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
            key = scanner.next().charAt( 0 );
            switch (key) {
                case 's':
                    circleArray.showQueue();
                    break;
                case 'a':
                    System.out.println( "输出一个数:" );
                    int value = scanner.nextInt();
                    circleArray.addQueue( value );
                    break;
                case 'g':
                    try {
                        int result = circleArray.getQueue();
                        System.out.printf( "取出数据是%d\n", result );
                    } catch (Exception e) {
                        System.out.println( e.getMessage() );
                    }
                    break;
                case 'h':
                    try {
                        int result = circleArray.headQueue();
                        System.out.printf( "队列头的数据是%d\n", result );
                    } catch (Exception e) {
                        System.out.println( e.getMessage() );
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println( "程序退出~！" );

    }


}

class CircleArray {
    /**
     * 表示数组最大容量
     */
    private int maxSize;
    /**
     * front变量做调整:front就指向队列的第一个元素，也是就说arr[fornt]
     * fornt 初始值=0
     */
    private int front;
    /**
     * rear变量做调整 ：real指向队列最好一个元素后一个位置，因为希望空出一个位置
     * rear 初始值=0
     */
    private int rear;
    /**
     * 该数据用于存放队列，模拟队列
     */
    private int[] arr;

    /**
     * 创建队列的构造器
     */
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否满
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，这里必须考虑取 %
        rear = (rear + 1) % maxSize;


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
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保存到一个临时变量
        //2.将front后移，考虑取模
        //3.将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
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
        //思路:从front开始遍历，遍历多少给元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf( "arr[%   d]=%d\n", i % maxSize, arr[i % maxSize] );
        }
    }

    /**
     * 求出当前队列的有效个数
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
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
        return arr[front];
    }
}