package com.liu.callable;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * callAble线程支持线程返回值
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyCallable myCallable = new MyCallable();

        FutureTask<Map<String, String>> mapFutureTask = new FutureTask<>(myCallable);

        new Thread(mapFutureTask).start();

        Map<String, String> stringStringMap = mapFutureTask.get();// 获取线程的返回结果 阻塞获取
        System.out.println(stringStringMap);
    }
}
