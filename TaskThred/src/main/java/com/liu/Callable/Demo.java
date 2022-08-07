package com.liu.Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("线程执行中...");
                Thread.sleep(1000);
                return 100;
            }
        });
        task.run();
        // 这个get是阻塞的,会等待线程结果
        Integer integer = task.get();
        System.out.println("Main线程被阻塞");
        System.out.println("线程执行完毕");
    }
}
