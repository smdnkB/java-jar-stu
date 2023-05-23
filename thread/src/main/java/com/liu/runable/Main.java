package com.liu.runable;

public class Main {

    /**
     * 和 Thread 类功能一样 只是通过接口实现
     */
    public static void main(String[] args) {

        MyRunnable myRunnable = new MyRunnable();

        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}
