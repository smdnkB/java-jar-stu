package com.liu.Runnable;

public class Demo {
    public static void main(String[] args) {
        RunnableTask runnableTask = new RunnableTask();
        Thread thread = new Thread(runnableTask);
        thread.start();
        System.out.println("主线程结束");
    }
}
