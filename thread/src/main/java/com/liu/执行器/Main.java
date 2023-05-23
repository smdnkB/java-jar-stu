package com.liu.执行器;

import com.liu.callable.MyCallable;

import java.util.Map;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 核心线程数  最大线程数  超时未活跃关闭线程  线程队列（未来得及执行的线程放入队列）
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, 5, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10)
        ) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                // 在call方法中执行错误可以在此处处理
                super.afterExecute(r, t);
                System.out.println("任务执行完毕");
            }
        };

        Future<Map<String, String>> submit = executor.submit(new MyCallable());
        Map<String, String> stringStringMap = submit.get();
        System.out.println(stringStringMap);
        executor.shutdown();
    }
}
