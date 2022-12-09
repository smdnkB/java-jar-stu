package com.liu.Thread;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Mythread mythread = new Mythread("task_one");

        System.out.println(mythread.getState());  // NEW  RUNNABLE  RUNNING WAITING

        mythread.start();
        System.out.println(mythread.getState());

        Thread.yield(); // 线程从RUNNING 变为 RUNNABLE

        mythread.join(); // 阻塞 ,直到线程结束

        mythread.interrupt(); // 打断阻塞的线程, 被打断的线程会抛出一个异常
        System.out.println("主线程结束");
    }
}
