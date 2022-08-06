package com.liu.Callable;

import java.util.concurrent.FutureTask;

public class Demo {
    public static void main(String[] args) {
        CallableTask callableTask = new CallableTask();
        FutureTask future = new FutureTask(callableTask);
        Thread thread = new Thread(future);
        thread.start();


        System.out.println("主线程结束");
    }
}
