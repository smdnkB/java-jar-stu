package com.liu.demo.export;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: liu long fei
 * @description: TODO 类描述
 * @date: 2023/6/24 10:25
 * @version: 1.0
 */
public class Main {


    // 核心线程数  最大线程数  超时未活跃关闭线程  线程队列（未来得及执行的线程放入队列）
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            5, 5, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10)
    ) {
        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            // 在call方法中执行错误可以在此处处理
            super.afterExecute(r, t);
            System.out.println("任务执行完毕");
        }
    };

    public static void main(String[] args) {

        executor.submit(new TypeAExport());
    }
}
