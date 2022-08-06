package com.liu.Thread;

public class Demo {
    public static void main(String[] args) {
        Mythread mythread = new Mythread("task_one");
        mythread.start();
        System.out.println("主线程结束");
    }
}
