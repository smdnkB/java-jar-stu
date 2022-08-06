package com.liu.pro.forkjoinpool;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ForkJoinPoolStu {
    public static void main(String[] args) throws Exception {
        testHasResultTask();
    }

    public static void testHasResultTask() throws Exception {

        int result1 = 0;
        long star = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++) {
            result1 += i;
        }
        System.out.println("循环计算 1-1000000 累加值：" + result1+"耗时: "+(System.currentTimeMillis()-star));


        star = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> task = pool.submit(new MyTask(1, 1000000));
        int result2 = task.get();
        System.out.println("并行计算 1-1000000 累加值：" + result2+"耗时: "+(System.currentTimeMillis()-star));
        pool.awaitTermination(2, TimeUnit.SECONDS);
        pool.shutdown();
    }
}
